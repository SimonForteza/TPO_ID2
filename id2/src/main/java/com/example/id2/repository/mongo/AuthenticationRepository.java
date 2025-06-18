package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.AuthenticationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends MongoRepository <AuthenticationModel, String> {
    Optional<AuthenticationModel> findByEmail (String email);
}
