package com.krasimirkolchev.examm.services.impl;

import com.krasimirkolchev.examm.models.entities.Classification;
import com.krasimirkolchev.examm.models.serviceModels.ClassificationServiceModel;
import com.krasimirkolchev.examm.repositories.ClassificationsRepository;
import com.krasimirkolchev.examm.services.ClassificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationsRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClassificationServiceImpl(ClassificationsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        if (repository.count() == 0) {
            repository.saveAndFlush(new Classification("BUG"));
            repository.saveAndFlush(new Classification("FEATURE"));
            repository.saveAndFlush(new Classification("SUPPORT"));
            repository.saveAndFlush(new Classification("OTHER"));
        }
    }

    @Override
    public List<ClassificationServiceModel> getAllClassifications() {
        return this.repository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, ClassificationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Classification getClassificationById(String classificationId) {
        return this.repository.getOne(classificationId);
    }
}
