package com.krasimirkolchev.examm.services;

import com.krasimirkolchev.examm.models.viewModels.TaskViewModel;
import com.krasimirkolchev.examm.models.serviceModels.TaskServiceModel;
import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;

import java.util.List;

public interface TaskService {

    TaskServiceModel addTask(TaskServiceModel taskServiceModel, UserServiceModel userServiceModel);

    List<TaskViewModel> getAllTasks();

    void changeProgress(String id);
}
