package com.krasimirkolchev.examm.models.bindingModels;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TaskAddBindingModel {
    private String name;
    private String description;
    private Date dueDate;
    private String classification;

    public TaskAddBindingModel() {
    }

    @Length(min = 3, max = 20, message = "Task name length must be between 3 and 20 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5, message = "Description length must be at least 5 symbols!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "You must select date!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date cannot be in the past")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @NotBlank(message = "Classification must be selected!")
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
