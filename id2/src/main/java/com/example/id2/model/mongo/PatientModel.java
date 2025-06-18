package com.example.id2.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Map;

@Document(collection = "patients")
public class PatientModel {

    @Id
    private String dni;

    private String name;

    private String address;

    private String age;

    private Map<String, Object> jsonData;

    public PatientModel(String dni, String name, String address, String age, Map<String, Object> jsonData) {
        this.dni = dni;
        this.name = name;
        this.address = address;
        this.age = age;
        this.jsonData = jsonData;
    }

    public PatientModel() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }
}