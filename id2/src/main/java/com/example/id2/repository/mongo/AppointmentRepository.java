package com.example.id2.repository.mongo;

import com.example.id2.model.mongo.AppointmentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<AppointmentModel, String> {
    List<AppointmentModel> findByProfessionalDniAndState(String professionalDni, String state);

}
