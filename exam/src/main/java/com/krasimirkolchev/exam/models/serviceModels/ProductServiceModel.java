package com.krasimirkolchev.exam.models.serviceModels;

import com.krasimirkolchev.exam.models.entities.Category;

import java.time.LocalDate;

public class ProductServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private Double price;
    private LocalDate neededBefore;
    private Category category;

    public ProductServiceModel() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
