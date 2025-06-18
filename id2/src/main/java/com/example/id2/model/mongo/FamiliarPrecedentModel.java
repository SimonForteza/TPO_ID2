package com.example.id2.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "familiar_precedent")
public class FamiliarPrecedentModel {

    @Id
    private String id;

    private String patientDni;

    private Enum relationship; //TODO

    private Map<String, Object> precedents;
}