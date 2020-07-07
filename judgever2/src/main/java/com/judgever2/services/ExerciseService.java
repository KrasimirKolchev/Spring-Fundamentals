package com.judgever2.services;

import com.judgever2.models.entities.Exercise;
import com.judgever2.models.serviceModels.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {

    Exercise getById(String id);

    boolean existByExerciseName(String name);

    ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel);

    List<ExerciseServiceModel> getAllEx();
}
