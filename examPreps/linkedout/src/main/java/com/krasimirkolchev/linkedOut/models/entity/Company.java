package com.krasimirkolchev.linkedOut.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
    private Integer budget;
    private String description;
    private String name;
    private String town;
    private List<Employee> employees;

    public Company() {
    }

    @Column(name = "budget", nullable = false)
    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Column(name = "description", columnDefinition = "text", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "town", nullable = false)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "company")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
