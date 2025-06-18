package com.example.id2.model.neo;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class FamilyRelationship {

    @Id
    @GeneratedValue
    private String id;

    @TargetNode
    private PatientNeoModel familyMember;

    private RelationshipWeight relationshipWeight; //enum

    public FamilyRelationship(String id, PatientNeoModel familyMember, RelationshipWeight relationshipWeight) {
        this.id = id;
        this.familyMember = familyMember;
        this.relationshipWeight = relationshipWeight;
    }

    public FamilyRelationship() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PatientNeoModel getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(PatientNeoModel familyMember) {
        this.familyMember = familyMember;
    }

    public RelationshipWeight getRelationshipWeight() {
        return relationshipWeight;
    }

    public void setRelationshipWeight(RelationshipWeight relationshipWeight) {
        this.relationshipWeight = relationshipWeight;
    }
}
