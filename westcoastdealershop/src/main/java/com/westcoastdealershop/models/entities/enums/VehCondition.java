package com.westcoastdealershop.models.entities.enums;

public enum VehCondition {
    NEW("New"),
    USED("Used"),
    FOR_PARTS("For parts");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    VehCondition(String displayValue) {
        this.displayValue = displayValue;
    }
}
