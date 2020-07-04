package com.westcoastdealershop.models.serviceModels;

import com.westcoastdealershop.models.entities.Model;

import java.util.List;

public class BrandServiceModel {
    private String name;
    private List<Model> models;

    public BrandServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
