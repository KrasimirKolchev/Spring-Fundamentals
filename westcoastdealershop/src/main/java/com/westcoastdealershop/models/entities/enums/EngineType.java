package com.westcoastdealershop.models.entities.enums;

public enum EngineType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    EngineType(String displayValue) {
        this.displayValue = displayValue;
    }
}
