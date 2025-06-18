package com.example.id2.model.neo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Patient")
@Data
public class PatientModel {

    @Id
    private String dni;

    @Relationship(type = "CONSULTS", direction = Relationship.Direction.INCOMING)
    private Set<ProfessionalModel> professionals = new HashSet<>();
}
