package com.example.id2.model.cassandra;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table("patient_sensor_status")
public class PatientSensorStatusModel {

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "sensor_type", type = PrimaryKeyType.PARTITIONED)
        private String sensorType;

        @PrimaryKeyColumn(name = "dni", type = PrimaryKeyType.PARTITIONED)
        private String dni;

        @PrimaryKeyColumn(name = "timestamp", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        private Instant timestamp;

        public Key(String sensorType, String dni, Instant timestamp) {
            this.sensorType = sensorType;
            this.dni = dni;
            this.timestamp = timestamp;
        }

        public Key() {
        }

        public String getSensorType() {
            return sensorType;
        }

        public void setSensorType(String sensorType) {
            this.sensorType = sensorType;
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }
    }

    @PrimaryKey
    private Key key;

    private String statusValue;

    public PatientSensorStatusModel(Key key, String statusValue) {
        this.key = key;
        this.statusValue = statusValue;
    }

    public PatientSensorStatusModel() {
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
