package com.example.id2.model;

import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
public class FamilyRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Neo4jPatient familyMember;

    private RelationshipWeight relationshipWeight; //enum
}
