package com.example.id2.service;

import com.example.id2.model.Neo4jPatient;
import com.example.id2.model.Neo4jProfessional;
import com.example.id2.model.RelationshipWeight;

import java.util.List;

public interface RelationshipService {
    void establishConsultRelationship(String patientMongoId, String professionalMongoId);

    void establishFamilyRelationship(String patient1MongoId, String patient2MongoId, RelationshipWeight relationshipWeight);

    List<Neo4jPatient> getFamilyMembers(String patientMongoId);

    List<Neo4jPatient> getFamilyMembersByMaxWeight(String patientMongoId, RelationshipWeight maxWeight);

    List<Neo4jPatient> getFamilyMembersByType(String patientMongoId, RelationshipWeight relationshipWeight);
}
