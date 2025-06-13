package com.example.id2.repository;

import com.example.id2.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientModel, String> {
}
