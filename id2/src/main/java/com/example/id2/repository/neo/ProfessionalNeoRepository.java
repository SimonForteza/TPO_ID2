package com.example.id2.repository.neo;

import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.ProfessionalNeoModel;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalNeoRepository extends Neo4jRepository<ProfessionalNeoModel, String> {
    Optional<ProfessionalNeoModel> findByDni(String dni);

    @Query("MATCH (prof:Professional {dni: $professionalId})-[:CONSULTS]->(p:Patient) RETURN p")
    Optional<List<PatientNeoModel>> findPatientsConsultedByProfessional(String professionalId);

    @Query("MATCH (p:Patient {mongoId: $patientId}) " +
            "MATCH (prof:Professional {mongoId: $professionalId}) " +
            "MERGE (prof)-[r:CONSULTS]->(p) " +
            "RETURN prof")
    ProfessionalNeoModel createConsultsRelationship(String patientId, String professionalId);
}
