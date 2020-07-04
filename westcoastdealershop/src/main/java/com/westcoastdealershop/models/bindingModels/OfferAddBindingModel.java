package com.westcoastdealershop.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OfferAddBindingModel {
    private String description;
    private String engine;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private String transmission;
    private Integer year;
    private String model;
    private String condition;
    private String sellerId;

    public OfferAddBindingModel() {
    }

    @Length(min = 10, max = 512, message = "Description must be between 10 and 512 symbols long!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank(message = "Engine type must be selected!")
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }


    @Length(min = 2, max = 512, message = "Add image url!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    @Positive(message = "Mileage must be above 0!")
    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    @NotNull
    @Positive(message = "Price must be above 0!")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotBlank(message = "Transmission type must be selected!")
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @NotNull
    @Min(value = 1900, message = "Manufacturing year must be after 1900y.")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @NotBlank(message = "Model must be selected!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotBlank(message = "Condition must be selected!")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
