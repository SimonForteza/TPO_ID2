package com.example.id2.repository;

import com.example.id2.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<PatientModel, String> {
    Optional<PatientModel> findByDni (String dni);
}
