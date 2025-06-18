package com.example.id2.repository.neo;

import com.example.id2.model.neo.ProfessionalModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalNeoRepository extends Neo4jRepository<ProfessionalModel, String> {
}
