package com.example.id2.model.neo;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Patient")
public class PatientNeoModel {
    @Id
    private String dni;

    @Relationship(type = "CONSULTS", direction = Relationship.Direction.INCOMING)
    private Set<ProfessionalNeoModel> professionals = new HashSet<>();

    @Relationship(type = "IS_FAMILY_OF", direction = Relationship.Direction.OUTGOING)
    private Set<FamilyRelationship> familyMembers = new HashSet<>();

    public PatientNeoModel(String dni, Set<ProfessionalNeoModel> professionals, Set<FamilyRelationship> familyMembers) {
        this.dni = dni;
        this.professionals = professionals;
        this.familyMembers = familyMembers;
    }

    public PatientNeoModel() {
    }

    public PatientNeoModel(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<ProfessionalNeoModel> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(Set<ProfessionalNeoModel> professionals) {
        this.professionals = professionals;
    }

    public Set<FamilyRelationship> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Set<FamilyRelationship> familyMembers) {
        this.familyMembers = familyMembers;
    }
}



