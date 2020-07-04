package com.westcoastdealershop.models.entities.enums;

public enum TransmissionType {
    MANUAL("Manual"),
    AUTOMATIC("Automatic"),
    SEMI_AUTOMATIC("Semi-automatic");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    TransmissionType(String displayValue) {
        this.displayValue = displayValue;
    }
}
