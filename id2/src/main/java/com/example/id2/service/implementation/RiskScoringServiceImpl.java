package com.example.id2.service.implementation;

import com.example.id2.model.neo.FamilyRelationship;
import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.mongo.FamiliarPrecedentModel;
import com.example.id2.repository.neo.PatientNeoRepository;
import com.example.id2.repository.mongo.FamiliarPrecedentMongoRepository;
import com.example.id2.service.RelationshipService;
import com.example.id2.service.RiskScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RiskScoringServiceImpl implements RiskScoringService {

    @Autowired
    private PatientNeoRepository patientNeoRepository;

    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private FamiliarPrecedentMongoRepository familiarPrecedentMongoRepository;

    @Override
    public Map<String, Object> calculateRiskScore(String patientId) {
        Map<String, Object> result = new HashMap<>();
        List<PatientNeoModel> familyMembers = relationshipService.getFamilyMembers(patientId);

        double totalRiskScore = 0;
        int familyMemberCount = 0; // Renamed for clarity, it represents the count of family members contributing to the score
        List<Map<String, Object>> familyDetails = new ArrayList<>();

        for (PatientNeoModel familyMember : familyMembers) {
            // Get relationship weight for the current family member
            int relationshipWeight = 5; // Default value
            for (FamilyRelationship rel : familyMember.getFamilyMembers()) {
                if (rel.getFamilyMember().getDni().equals(patientId)) {
                    relationshipWeight = rel.getRelationshipWeight().getWeight();
                    break;
                }
            }

            // Get disease count for the family member from MongoDB
            int diseaseCount = familiarPrecedentMongoRepository.findByPatientDni(familyMember.getDni())
                    .map(FamiliarPrecedentModel::getPrecedents)
                    .map(Map::size)
                    .orElse(0);

            // Calculate member score: relationshipWeight * diseaseCount
            double memberScore = (double) diseaseCount * relationshipWeight; // Changed from division to multiplication

            totalRiskScore += memberScore;
            familyMemberCount += 1;

            // Add family member details
            Map<String, Object> familyDetail = new HashMap<>();
            familyDetail.put("patientMongoId", familyMember.getDni());
            familyDetail.put("relationshipWeight", relationshipWeight);
            familyDetail.put("precedentCount", diseaseCount);
            familyDetail.put("score", memberScore);
            familyDetails.add(familyDetail);
        }

        // Calculate final score
        // Adjusted the final score calculation: (totalRiskScore / familyMemberCount) is the average score per family member.
        // Multiplying by 5 gives a range similar to what you might expect if weights are 1-5 and diseases are few.
        // You might need to adjust the multiplier (5) based on your desired final score range.
        double finalScore = familyMemberCount > 0 ? (totalRiskScore / familyMemberCount) * 5 : 0;

        // Determine risk level
        String riskLevel;
        if (finalScore < 5) {
            riskLevel = "BAJO";
        } else if (finalScore < 10) {
            riskLevel = "MEDIO";
        } else if (finalScore < 15) {
            riskLevel = "ALTO";
        } else {
            riskLevel = "CRÍTICO";
        }

        result.put("patientId", patientId);
        result.put("totalScore", finalScore);
        result.put("riskLevel", riskLevel);
        result.put("familyDetails", familyDetails);

        return result;
    }
}
