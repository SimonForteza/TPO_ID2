package com.example.id2.repository.neo;

import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.ProfessionalNeoModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientNeoRepository extends Neo4jRepository<PatientNeoModel, String> {

    Optional<PatientNeoModel> findByDni(String dni);

    @Query("MATCH (p:Patient {dni: $dni})<-[:CONSULTS]-(prof:Professional) RETURN prof")
    Optional<List<ProfessionalNeoModel>> findProfessionalsConsultingPatient(String dni);

    @Query("MATCH (p1:Patient {dni: $patient1Dni}), (p2:Patient {dni: $patient2Dni}) " +
            "CREATE (p1)-[r:IS_FAMILY_OF {weight: $weight}]->(p2)")
    void createFamilyRelationship(String patient1Dni, String patient2Dni, int weight);

    @Query("MATCH (p:Patient {dni: $dni})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "RETURN family, r.weight as weight " +
            "ORDER BY r.weight")
    Optional<List<PatientNeoModel>> findFamilyMembers(String dni);

    @Query("MATCH (p:Patient {dni: $dni})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "WHERE r.weight <= $maxWeight " +
            "RETURN family, r.weight as weight " +
            "ORDER BY r.weight")
    List<PatientNeoModel> findFamilyMembersByMaxWeight(String dni, int maxWeight);

    @Query("MATCH (p:Patient {dni: $dni})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "WHERE r.weight = $weight " +
            "RETURN family, r.weight as weight")
    List<PatientNeoModel> findFamilyMembersByType(String dni, int weight);
}
