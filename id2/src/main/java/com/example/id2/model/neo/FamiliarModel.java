package com.example.id2.model.neo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Familiar")
@Data
public class FamiliarModel {

    @Id
    private String id;

    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.INCOMING)
    private Set<ProfessionalNeoModel> professionals = new HashSet<>(); //TODO
}