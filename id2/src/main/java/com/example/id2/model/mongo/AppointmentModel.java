package com.example.id2.model.mongo;

import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "appointments")
public class AppointmentModel {

    @Id
    @GeneratedValue
    private String id;

    private LocalDateTime date;

    private String professionalDni;

    private String patientDni;

    private String state;

    public AppointmentModel(String professionalDni, String patientDni) {
        this.professionalDni = professionalDni;
        this.patientDni = patientDni;
        date = LocalDateTime.now().plusDays(3);
        state = "REGISTER";
    }

    public AppointmentModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getProfessionalDni() {
        return professionalDni;
    }

    public void setProfessionalDni(String professionalDni) {
        this.professionalDni = professionalDni;
    }

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
