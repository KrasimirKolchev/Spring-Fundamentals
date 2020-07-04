package com.krasimirkolchev.andreysshop.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private ClothCategory clothCategory;
    private Gender gender;
    private String imgSrc;

    public Item() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne
    public ClothCategory getClothCategory() {
        return clothCategory;
    }

    public void setClothCategory(ClothCategory clothCategory) {
        this.clothCategory = clothCategory;
    }

    @Enumerated
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
