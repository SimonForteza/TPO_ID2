package com.example.id2.repository.neo;

import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.ProfessionalNeoModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientNeoRepository extends Neo4jRepository<PatientNeoModel, String> {

    @Query("""
        MATCH (p:Patient {dni: $dni})<-[:CONSULTS]-(prof:Professional)
        RETURN prof
        """)
    Optional<Set<ProfessionalNeoModel>> findProfessionalsByPatientDni (String dni);
}
