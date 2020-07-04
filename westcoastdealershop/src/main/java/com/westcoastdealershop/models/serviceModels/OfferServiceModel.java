package com.westcoastdealershop.models.serviceModels;

import com.westcoastdealershop.models.entities.Model;
import com.westcoastdealershop.models.entities.enums.VehCondition;
import com.westcoastdealershop.models.entities.enums.EngineType;
import com.westcoastdealershop.models.entities.enums.TransmissionType;

public class OfferServiceModel extends BaseServiceModel {
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private TransmissionType transmission;
    private Integer year;
    private Model model;
    private UserServiceModel seller;
    private VehCondition vehCondition;

    public OfferServiceModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public UserServiceModel getSeller() {
        return seller;
    }

    public void setSeller(UserServiceModel seller) {
        this.seller = seller;
    }

    public VehCondition getVehCondition() {
        return vehCondition;
    }

    public void setVehCondition(VehCondition vehCondition) {
        this.vehCondition = vehCondition;
    }
}
