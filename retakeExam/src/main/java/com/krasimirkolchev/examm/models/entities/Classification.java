package com.krasimirkolchev.examm.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {
    private String classificationName;
    private String description;

    public Classification() {
    }

    public Classification(String name) {
        this.classificationName = name;
    }

    @Column(name = "classification_name", unique = true, nullable = false)
    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
