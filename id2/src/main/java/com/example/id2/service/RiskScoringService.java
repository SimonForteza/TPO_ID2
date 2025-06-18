package com.example.id2.service;

import java.util.Map;

public interface RiskScoringService {
    Map<String, Object> calculateRiskScore(String patientId);
}
