package com.judgever2.services.impl;

import com.judgever2.models.entities.Exercise;
import com.judgever2.models.serviceModels.ExerciseServiceModel;
import com.judgever2.repositories.ExerciseRepository;
import com.judgever2.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Exercise getById(String id) {
        return this.exerciseRepository.getOne(id);
    }

    @Override
    public boolean existByExerciseName(String name) {
        return this.exerciseRepository.existsByName(name);
    }

    @Override
    public ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel) {
        this.exerciseRepository.save(this.modelMapper.map(exerciseServiceModel, Exercise.class));
        return exerciseServiceModel;
    }

    @Override
    public List<ExerciseServiceModel> getAllEx() {
        return this.exerciseRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, ExerciseServiceModel.class))
                .collect(Collectors.toList());
    }
}
