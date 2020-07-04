package com.westcoastdealershop.models.entities.enums;

public enum Category {
    CAR("Car"),
    TRUCK("Truck"),
    BUS("Bus"),
    MOTORCYCLE("Motorcycle");

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    Category(String displayValue) {
        this.displayValue = displayValue;
    }
}
