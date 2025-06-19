package com.example.id2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;

public class AddPatientSensorDataDto implements Serializable {

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("sensorType")
    private String sensorType;

    @JsonProperty("statusValue")
    private String statusValue;

    private Instant timestamp;

    public AddPatientSensorDataDto(String dni, String sensorType, String statusValue) {
        this.dni = dni;
        this.sensorType = sensorType;
        this.statusValue = statusValue;
        this.timestamp = Instant.now();
    }

    public AddPatientSensorDataDto() {
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
