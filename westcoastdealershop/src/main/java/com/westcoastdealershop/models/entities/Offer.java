package com.westcoastdealershop.models.entities;

import com.westcoastdealershop.models.entities.enums.VehCondition;
import com.westcoastdealershop.models.entities.enums.EngineType;
import com.westcoastdealershop.models.entities.enums.TransmissionType;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Long mileage;
    private Double price;
    private TransmissionType transmission;
    private Integer year;
    private Model model;
    private User seller;
    private VehCondition vehCondition;

    public Offer() {
    }

    @Column(name = "description", columnDefinition = "varchar(512)", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "engine", nullable = false)
    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "mileage", nullable = false)
    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "transmission", nullable = false)
    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @ManyToOne
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Column(name = "vehicle_condition", nullable = false)
    public VehCondition getVehCondition() {
        return vehCondition;
    }

    public void setVehCondition(VehCondition vehCondition) {
        this.vehCondition = vehCondition;
    }
}
