package com.judgever2.services;

import com.judgever2.models.serviceModels.HomeworkServiceModel;

import java.util.Set;

public interface HomeworkService {

    HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel);

    HomeworkServiceModel getHomeworkToCheck();

    HomeworkServiceModel getHomeworkById(String homeworkId);

    Set<String> getHomeworksByUser(String userId);
}
