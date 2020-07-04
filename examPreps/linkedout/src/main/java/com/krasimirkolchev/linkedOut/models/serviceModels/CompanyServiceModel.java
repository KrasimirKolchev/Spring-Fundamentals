package com.krasimirkolchev.linkedOut.models.serviceModels;

import java.util.List;

public class CompanyServiceModel extends BaseServiceModel {
    private Integer budget;
    private String description;
    private String name;
    private String town;
    private List<EmployeeServiceModel> employees;

    public CompanyServiceModel() {
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<EmployeeServiceModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeServiceModel> employees) {
        this.employees = employees;
    }
}
