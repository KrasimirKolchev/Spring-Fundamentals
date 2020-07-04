package com.krasimirkolchev.exam.models.serviceModels;

public class CategoryServiceModel extends BaseServiceModel {
    private String name;
    private String description;

    public CategoryServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
