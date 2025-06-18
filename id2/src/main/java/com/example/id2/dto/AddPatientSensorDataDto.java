package com.example.id2.dto;

import java.io.Serializable;
import java.time.Instant;

public class AddPatientSensorDataDto implements Serializable {

    private String dni;
    private String sensorType;
    private String statusValue;
    private Instant timestamp;

    public AddPatientSensorDataDto(String dni, String sensorType, String statusValue, Instant timestamp) {
        this.dni = dni;
        this.sensorType = sensorType;
        this.statusValue = statusValue;
        this.timestamp = Instant.now();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
