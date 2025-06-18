package com.example.id2.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Appointments")
public class AppointmentModel {
    @Id
    private String id;
    private LocalDateTime date;
    private String professionalDni;
    private String patientDni;
    private String state;

    public AppointmentModel(String professionalDni, String patientDni) {
        this.professionalDni = professionalDni;
        this.patientDni = patientDni;
        date = LocalDateTime.now();
        state = "REGISTER";
    }
}
