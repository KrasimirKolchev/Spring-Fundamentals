package com.westcoastdealershop.services.impl;

import com.westcoastdealershop.models.entities.Model;
import com.westcoastdealershop.models.entities.enums.Category;
import com.westcoastdealershop.models.serviceModels.ModelServiceModel;
import com.westcoastdealershop.repositories.ModelRepository;
import com.westcoastdealershop.services.BrandService;
import com.westcoastdealershop.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArrayList<ModelServiceModel> getAllModels() {
        return this.modelRepository.findAll().stream()
                .map(m -> this.modelMapper.map(m, ModelServiceModel.class)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Model getModelById(String id) {
        return this.modelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Model with ID: %s doesn't exist!", id)));
    }

    @Override
    public Model getModelByName(String name) {
        return this.modelRepository.findByName(name);
    }

    @Override
    public ModelServiceModel createModel(ModelServiceModel modelServiceModel) {
        if (this.modelRepository.findByName(modelServiceModel.getName()) != null) {
            throw new EntityExistsException("modelFound");
        }

        Model model = this.modelMapper.map(modelServiceModel, Model.class);
        model.setCreated(new Date());
        model.setModified(new Date());
        model.setCategory(Category.valueOf(modelServiceModel.getCategory()));
        model.setBrand(this.brandService.getBrandByName(modelServiceModel.getBrand()));

        return this.modelMapper.map(this.modelRepository.save(model), ModelServiceModel.class);
    }

    @Override
    public Model updateModel(Model model) {
        model.setModified(new Date());
        return this.modelRepository.save(model);
    }

    @Override
    public Model deleteModel(String id) {
        Model model = this.getModelById(id);
        this.modelRepository.deleteById(id);
        return model;
    }

    @Override
    public long modelsCount() {
        return this.modelRepository.count();
    }

    @Override
    public void createModelsBatch(List<ModelServiceModel> sampleModels) {
        sampleModels.stream()
                .sorted(Comparator.comparing(ModelServiceModel::getName))
                .forEach(m -> {
            Model model = this.modelMapper.map(m, Model.class);
            model.setCreated(new Date());
            model.setModified(new Date());
            model.setCategory(Category.valueOf(m.getCategory()));
            model.setBrand(this.brandService.getBrandByName(m.getBrand()));
            this.modelRepository.save(model);
        });
    }

}
