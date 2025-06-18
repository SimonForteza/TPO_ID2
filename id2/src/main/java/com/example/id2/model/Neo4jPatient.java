package com.example.id2.model;

import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.HashSet;
import java.util.Set;
@Node("Patient")
@Data
public class Neo4jPatient {
    @Id
    private String mongoId;

    // Relationship between patient and professional
    @Relationship(type = "CONSULTS", direction = Relationship.Direction.INCOMING)
    private Set<Neo4jProfessional> professionals = new HashSet<>();

    // Relationship between family members
    @Relationship(type = "IS_FAMILY_OF", direction = Relationship.Direction.OUTGOING)
    private Set<FamilyRelationship> familyMembers = new HashSet<>();
}



