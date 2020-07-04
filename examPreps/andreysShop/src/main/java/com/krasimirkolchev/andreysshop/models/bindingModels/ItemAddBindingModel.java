package com.krasimirkolchev.andreysshop.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemAddBindingModel {
    private String name;
    private String description;
    private Double price;
    private String category;
    private String gender;

    public ItemAddBindingModel() {
    }

    @Length(min = 2, message = "Item name must be at least 2 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, message = "Item description must be at least 2 symbols!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Price must be above 0!")
    @Positive(message = "Price must be above 0!")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotBlank(message = "Cloth category must be selected!")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotBlank(message = "Gender must be selected!")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
