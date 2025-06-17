package com.example.id2.repository;

import com.example.id2.model.AuthenticationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends MongoRepository<AuthenticationModel, String> {
    Optional<AuthenticationModel> findByEmail (String email);
}
