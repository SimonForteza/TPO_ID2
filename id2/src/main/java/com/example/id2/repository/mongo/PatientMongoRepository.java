package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientMongoRepository extends MongoRepository <PatientModel, String> {
    Optional<PatientModel> findByDni (String dni);
}
