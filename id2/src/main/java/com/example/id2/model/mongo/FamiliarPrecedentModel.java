package com.example.id2.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "familiar_precedent")
public class FamiliarPrecedentModel {

    @Id
    private String id;

    private String patientDni;

    private String relationship;

    private Map<String, Object> precedents;

    public FamiliarPrecedentModel(String id, String patientDni, String relationship, Map<String, Object> precedents) {
        this.id = id;
        this.patientDni = patientDni;
        this.relationship = relationship;
        this.precedents = precedents;
    }

    public FamiliarPrecedentModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Map<String, Object> getPrecedents() {
        return precedents;
    }

    public void setPrecedents(Map<String, Object> precedents) {
        this.precedents = precedents;
    }
}