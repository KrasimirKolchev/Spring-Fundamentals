package com.krasimirkolchev.andreysshop.models.serviceModels;

public class ClothCategoryServiceModel extends BaseServiceModel {
    private String name;
    private String description;

    public ClothCategoryServiceModel() {
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
