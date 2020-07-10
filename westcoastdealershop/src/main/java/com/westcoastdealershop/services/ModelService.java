package com.westcoastdealershop.services;

import com.westcoastdealershop.models.entities.Model;
import com.westcoastdealershop.models.serviceModels.ModelServiceModel;

import java.util.ArrayList;
import java.util.List;

public interface ModelService {
    ArrayList<ModelServiceModel> getAllModels();

    Model getModelById(String id);

    Model getModelByName(String name);

    ModelServiceModel createModel(ModelServiceModel modelServiceModel);

    Model updateModel(Model model);

    Model deleteModel(String id);

    long modelsCount();

    boolean modelExistByName(String name);

    void createModelsBatch(List<ModelServiceModel> sampleModels);
}
