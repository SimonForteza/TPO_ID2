package com.example.id2.model.neo;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Professional")
public class ProfessionalModel {

    @Id
    private String dni;

    @Relationship(type = "CONSULTS", direction = Relationship.Direction.OUTGOING)
    private Set<PatientModel> patients = new HashSet<>();

    public ProfessionalModel(String dni, Set<PatientModel> patients) {
        this.dni = dni;
        this.patients = patients;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<PatientModel> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientModel> patients) {
        this.patients = patients;
    }
}
