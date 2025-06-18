package com.example.id2.service;

import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.RelationshipWeight;

import java.util.List;

public interface RelationshipService {
    void establishFamilyRelationship(String patient1MongoId, String patient2MongoId, RelationshipWeight relationshipWeight);

    List<PatientNeoModel> getFamilyMembers(String patientMongoId);

    List<PatientNeoModel> getFamilyMembersByMaxWeight(String patientMongoId, RelationshipWeight maxWeight);

    List<PatientNeoModel> getFamilyMembersByType(String patientMongoId, RelationshipWeight relationshipWeight);
}
