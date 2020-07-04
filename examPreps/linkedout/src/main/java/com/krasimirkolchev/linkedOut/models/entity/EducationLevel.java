package com.krasimirkolchev.linkedOut.models.entity;

public enum EducationLevel {
    MASTER("Master"),
    BACHELOR("Bachelor"),
    SECONDARY("Secondary");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    EducationLevel(String displayValue) {
        this.displayValue = displayValue;
    }
}
