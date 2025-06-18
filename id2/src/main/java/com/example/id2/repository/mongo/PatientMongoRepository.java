package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.PatientMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientMongoRepository extends MongoRepository <PatientMongoModel, String> {
    Optional<PatientMongoModel> findByDni (String dni);
}
