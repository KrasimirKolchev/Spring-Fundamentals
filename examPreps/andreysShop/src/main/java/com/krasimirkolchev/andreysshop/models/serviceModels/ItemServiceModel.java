package com.krasimirkolchev.andreysshop.models.serviceModels;

import com.krasimirkolchev.andreysshop.models.entities.ClothCategory;
import com.krasimirkolchev.andreysshop.models.entities.Gender;

public class ItemServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private Double price;
    private ClothCategoryServiceModel clothCategoryServiceModel;
    private Gender gender;
    private String imgSrc;

    public ItemServiceModel() {
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

    public ClothCategoryServiceModel getClothCategoryServiceModel() {
        return clothCategoryServiceModel;
    }

    public void setClothCategoryServiceModel(ClothCategoryServiceModel clothCategoryServiceModel) {
        this.clothCategoryServiceModel = clothCategoryServiceModel;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
