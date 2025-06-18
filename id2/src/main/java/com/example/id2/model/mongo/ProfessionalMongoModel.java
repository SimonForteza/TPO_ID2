package com.example.id2.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Map;

@Document(collection = "professionals")
public class ProfessionalMongoModel {

    @Id
    private String dni;

    private String name;

    private String specialty;

    private Map<String, Object> jsonData;

    public ProfessionalMongoModel(String dni, String name, String specialty, Map<String, Object> jsonData) {
        this.dni = dni;
        this.name = name;
        this.specialty = specialty;
        this.jsonData = jsonData;
    }

    public ProfessionalMongoModel() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }
}