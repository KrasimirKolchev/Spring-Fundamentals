package com.krasimirkolchev.linkedOut.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompanyAddBindingModel {
    private Integer budget;
    private String description;
    private String name;
    private String town;

    public CompanyAddBindingModel() {
    }

    @NotNull(message = "Budget must be positive number!")
    @Positive(message = "Budget must be positive number!")
    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Length(min = 10, message = "Description must be at least 10 symbols!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 2, max = 10, message = "Company name must be between 2 and 10 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 2, max = 10, message = "Town name must be between 2 and 10 symbols!")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
