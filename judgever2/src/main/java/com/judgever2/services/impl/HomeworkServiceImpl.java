package com.judgever2.services.impl;

import com.judgever2.models.entities.Homework;
import com.judgever2.models.serviceModels.HomeworkServiceModel;
import com.judgever2.repositories.HomeworkRepository;
import com.judgever2.services.ExerciseService;
import com.judgever2.services.HomeworkService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService,
                               UserService userService, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel) {
        Homework homework = this.modelMapper.map(homeworkServiceModel, Homework.class);
        homework.setAuthor(this.userService.findUserById(homeworkServiceModel.getAuthor()));

        this.homeworkRepository.saveAndFlush(homework);

        return this.modelMapper.map(homework, HomeworkServiceModel.class);
    }

    @Override
    public HomeworkServiceModel getHomeworkToCheck() {
        return this.homeworkRepository.findAll()
                .stream()
                .min(Comparator.comparingInt(a -> a.getComments().size()))
                .map(homework -> this.modelMapper.map(homework, HomeworkServiceModel.class))
                .orElse(null);
    }

    @Override
    public HomeworkServiceModel getHomeworkById(String homeworkId) {
        return this.modelMapper.map(this.homeworkRepository.getOne(homeworkId), HomeworkServiceModel.class);
    }

    @Override
    public Set<String> getHomeworksByUser(String userId) {
        return this.homeworkRepository.getAllByAuthor_Id(userId)
                .stream()
                .map(h -> h.getExercise().getName())
                .collect(Collectors.toSet());
    }
}
