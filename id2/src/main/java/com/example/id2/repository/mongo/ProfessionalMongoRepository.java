package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.ProfessionalMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalMongoRepository extends MongoRepository<ProfessionalMongoModel, String> {
    Optional<List<ProfessionalMongoModel>> findBySpecialty (String specialty);
}
