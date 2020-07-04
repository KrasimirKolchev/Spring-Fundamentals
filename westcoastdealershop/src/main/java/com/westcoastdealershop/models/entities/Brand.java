package com.westcoastdealershop.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    private String name;
    private List<Model> models;

    public Brand() {
    }

    public Brand(String name, List<Model> models) {
        this.name = name;
        this.models = models;
    }

    public Brand(String name) {
        this.name = name;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public static Brand create(String name, List<Model> models) {
        Brand brand = new Brand(name);
        models.forEach(model -> {
            model.setBrand(brand);
            brand.getModels().add(model);
        });
        return brand;
    }

}
