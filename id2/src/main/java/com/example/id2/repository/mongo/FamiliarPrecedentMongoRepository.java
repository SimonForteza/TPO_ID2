package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.FamiliarPrecedentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamiliarPrecedentMongoRepository extends MongoRepository <FamiliarPrecedentModel, String> {
    Optional<FamiliarPrecedentModel> findByPatientDni(String dni);
}
