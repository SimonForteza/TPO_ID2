package com.example.id2.model.cassandra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table("patient_sensor_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientSensorStatusModel {

    @PrimaryKeyClass
    @Data
    public static class Key {
        @PrimaryKeyColumn(name = "sensor_type", type = PrimaryKeyType.PARTITIONED)
        private String sensorType;

        @PrimaryKeyColumn(name = "dni", type = PrimaryKeyType.PARTITIONED)
        private String dni;

        @PrimaryKeyColumn(name = "timestamp", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        private Instant timestamp;
    }

    @PrimaryKey
    private Key key;

    private String statusValue;
}
