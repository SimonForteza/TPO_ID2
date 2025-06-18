package com.example.id2.model.neo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Professional")
public class ProfessionalNeoModel {
    @Id
    private String dni;

    @Relationship(type = "CONSULTS", direction = Relationship.Direction.OUTGOING)
    private Set<PatientNeoModel> patients = new HashSet<>();

    public ProfessionalNeoModel(String dni, Set<PatientNeoModel> patients) {
        this.dni = dni;
        this.patients = patients;
    }

    public ProfessionalNeoModel(String dni) {
        this.dni = dni;
        this.patients = new HashSet<>();
    }

    public ProfessionalNeoModel() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<PatientNeoModel> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientNeoModel> patients) {
        this.patients = patients;
    }
}
