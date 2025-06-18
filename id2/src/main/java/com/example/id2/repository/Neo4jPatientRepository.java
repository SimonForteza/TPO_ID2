package com.example.id2.repository;

import com.example.id2.model.Neo4jPatient;
import com.example.id2.model.Neo4jProfessional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Neo4jPatientRepository extends Neo4jRepository<Neo4jPatient, String> {

    Optional<Neo4jPatient> findByMongoId(String mongoId);

    @Query("MATCH (p:Patient {mongoId: $patientId})<-[:CONSULTS]-(prof:Professional) RETURN prof")
    List<Neo4jProfessional> findProfessionalsConsultingPatient(String patientId);

    @Query("MATCH (p1:Patient {mongoId: $patient1Id}), (p2:Patient {mongoId: $patient2Id}) " +
            "CREATE (p1)-[r:IS_FAMILY_OF {weight: $weight}]->(p2)")
    void createFamilyRelationship(String patient1Id, String patient2Id, int weight);

    @Query("MATCH (p:Patient {mongoId: $patientId})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "RETURN family, r.weight as weight " +
            "ORDER BY r.weight")
    List<Neo4jPatient> findFamilyMembers(String patientId);

    @Query("MATCH (p:Patient {mongoId: $patientId})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "WHERE r.weight <= $maxWeight " +
            "RETURN family, r.weight as weight " +
            "ORDER BY r.weight")
    List<Neo4jPatient> findFamilyMembersByMaxWeight(String patientId, int maxWeight);

    @Query("MATCH (p:Patient {mongoId: $patientId})-[r:IS_FAMILY_OF]->(family:Patient) " +
            "WHERE r.weight = $weight " +
            "RETURN family, r.weight as weight")
    List<Neo4jPatient> findFamilyMembersByType(String patientId, int weight);
}
