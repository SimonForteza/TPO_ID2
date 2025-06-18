package com.example.id2.repository;

import com.example.id2.model.Neo4jPatient;
import com.example.id2.model.Neo4jProfessional;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Neo4jProfessionalRepository extends Neo4jRepository<Neo4jProfessional, String> {
    Optional<Neo4jProfessional> findByMongoId(String mongoId);

    @Query("MATCH (prof:Professional {mongoId: $professionalId})-[:CONSULTS]->(p:Patient) RETURN p")
    List<Neo4jPatient> findPatientsConsultedByProfessional(String professionalId);

    @Query("MATCH (p:Patient {mongoId: $patientId}) " +
            "MATCH (prof:Professional {mongoId: $professionalId}) " +
            "MERGE (prof)-[r:CONSULTS]->(p) " +
            "RETURN prof")
    Neo4jProfessional createConsultsRelationship(String patientId, String professionalId);
}
