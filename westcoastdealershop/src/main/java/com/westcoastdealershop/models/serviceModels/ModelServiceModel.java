package com.westcoastdealershop.models.serviceModels;

public class ModelServiceModel {
    private String name;
    private String category;
    private String imageUrl;
    private long startYear;
    private long endYear;
    private String brand;

    public ModelServiceModel() {
    }

    public ModelServiceModel(String brand, String name, String category, Integer startYear, Integer endYear, String imageUrl) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.startYear = startYear;
        this.endYear = endYear;
        this.imageUrl = imageUrl;
    }

    public ModelServiceModel(String brand, String name, String category, Integer startYear, String imageUrl) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.startYear = startYear;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getStartYear() {
        return startYear;
    }

    public void setStartYear(long startYear) {
        this.startYear = startYear;
    }

    public long getEndYear() {
        return endYear;
    }

    public void setEndYear(long endYear) {
        this.endYear = endYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
