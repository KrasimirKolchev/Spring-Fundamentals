package com.krasimirkolchev.linkedOut.models.serviceModels;

import com.krasimirkolchev.linkedOut.models.entity.EducationLevel;

import java.time.LocalDate;

public class EmployeeServiceModel extends BaseServiceModel {
    private LocalDate birthDate;
    private EducationLevel educationLevel;
    private String firstName;
    private String jobTitle;
    private String lastName;
    private Integer salary;
    private CompanyServiceModel company;

    public EmployeeServiceModel() {
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public CompanyServiceModel getCompany() {
        return company;
    }

    public void setCompany(CompanyServiceModel company) {
        this.company = company;
    }
}
