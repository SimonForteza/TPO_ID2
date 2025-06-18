package com.example.id2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Professional")
@Data
public class Neo4jProfessional {
    @Id
    private String mongoId;
    private String firstName;
    private String lastName;
    private String specialty;

    @Relationship(type = "CONSULTS", direction = Relationship.Direction.OUTGOING)
    private Set<Neo4jPatient> patients = new HashSet<>();
}
