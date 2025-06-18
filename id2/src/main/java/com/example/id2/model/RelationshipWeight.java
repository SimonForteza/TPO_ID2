package com.example.id2.model;

public enum RelationshipWeight {
    PARENT(1),      // Padre/Madre
    SIBLING(2),     // Hermano/Hermana
    UNCLE_AUNT(3),  // Tío/Tía
    COUSIN(4),      // Primo/Prima
    GRANDPARENT(5); // Abuelo/Abuela

    private final int weight;

    RelationshipWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
