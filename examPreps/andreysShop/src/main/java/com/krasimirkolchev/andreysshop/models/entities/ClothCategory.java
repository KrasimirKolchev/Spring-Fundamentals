package com.krasimirkolchev.andreysshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cloth_categories")
public class ClothCategory extends BaseEntity {
    private String name;
    private String description;

    public ClothCategory() {
    }

    public ClothCategory(String name, String description) {
        this.name = name;
        this.description = description;
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
}
