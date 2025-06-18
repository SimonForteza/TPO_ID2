package com.example.id2.model.mongo;

import org.springframework.data.neo4j.core.schema.Id;

import java.util.Map;

public class ProfessionalModel {

    @Id
    private String dni;

    private String name;

    private String specialty;

    private Map<String, Object> jsonData;
}
