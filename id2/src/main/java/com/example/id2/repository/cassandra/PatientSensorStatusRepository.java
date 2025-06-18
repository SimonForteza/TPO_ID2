package com.example.id2.repository.cassandra;

import com.example.id2.model.cassandra.PatientSensorStatusModel;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientSensorStatusRepository extends CassandraRepository <PatientSensorStatusModel, PatientSensorStatusModel.Key> {
}
