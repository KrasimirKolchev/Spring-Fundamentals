package com.westcoastdealershop.models.bindingModels;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ModelAddBindingModel {
    private String name;
    private String category;
    private String brand;
    private long startYear;
    private long endYear;
    private String imageUrl;

    public ModelAddBindingModel() {
    }

    @Length(min = 3, max = 40, message = "Model name must be between 3 and 40 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Image url cannot be empty!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotBlank(message = "Category must be selected!")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Min(value = 1900, message = "Year must be after 1900y.")
    public long getStartYear() {
        return startYear;
    }

    public void setStartYear(long startYear) {
        this.startYear = startYear;
    }

    @Min(value = 1900, message = "Year must be after 1900y.")
    public long getEndYear() {
        return endYear;
    }

    public void setEndYear(long endYear) {
        this.endYear = endYear;
    }

    @NotBlank(message = "Brand must be selected!")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
