package com.example.id2.service.implementation;

import com.example.id2.model.Neo4jPatient;
import com.example.id2.repository.Neo4jPatientRepository;
import com.example.id2.service.RelationshipService;
import com.example.id2.service.RiskScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RiskScoringServiceImpl implements RiskScoringService {

    @Autowired
    private Neo4jPatientRepository neo4jPatientRepository;

    @Autowired
    private RelationshipService relationshipService;

    @Override
    public Map<String, Object> calculateRiskScore(String patientId) {
        Map<String, Object> result = new HashMap<>();
        //  Obtengo todos los familiares del Paciente
        List<Neo4jPatient> familyMembers = relationshipService.getFamilyMembers(patientId);

        double totalRiskScore = 0;
        int totalWeight = 0;
        List<Map<String, Object>> familyDetails = new ArrayList<>();

        //  Itera cada familiar
        for (Neo4jPatient familyMember : familyMembers) {
            // Obtener el peso de la relación (1 para padres, 2 para hermanos, etc.)
            int relationshipWeight = familyMember.getFamilyMembers().stream()
                    .filter(rel -> rel.getFamilyMember().getMongoId().equals(patientId))
                    .findFirst()
                    .map(rel -> rel.getRelationshipWeight().getWeight())
                    .orElse(5); // Valor por defecto si no se encuentra la relación

            // Calcular score basado en la cantidad de enfermedades
            int diseaseCount = familyMember.getHistorialMedico().size();
            double memberScore = diseaseCount * (1.0 / relationshipWeight);

            totalRiskScore += memberScore;
            totalWeight += 1;

            //pacienteModel. -> Map<String, String>.

            // Agregar detalles del familiar
            Map<String, Object> familyDetail = new HashMap<>();
            familyDetail.put("name", familyMember.getFirstName() + " " + familyMember.getLastName());
            familyDetail.put("relationshipWeight", relationshipWeight);
            familyDetail.put("diseaseCount", diseaseCount);
            familyDetail.put("score", memberScore);
            familyDetails.add(familyDetail);
        }

        // Calcular score final
        double finalScore = totalWeight > 0 ? (totalRiskScore / totalWeight) * 20 : 0; // Multiplicamos por 20 para tener un rango más amplio

        // Determinar nivel de riesgo
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
