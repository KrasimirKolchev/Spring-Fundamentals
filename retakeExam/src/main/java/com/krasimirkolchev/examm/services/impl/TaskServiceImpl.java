package com.krasimirkolchev.examm.services.impl;

import com.krasimirkolchev.examm.models.viewModels.TaskViewModel;
import com.krasimirkolchev.examm.models.entities.Progress;
import com.krasimirkolchev.examm.models.entities.Task;
import com.krasimirkolchev.examm.models.serviceModels.TaskServiceModel;
import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.examm.repositories.TaskRepository;
import com.krasimirkolchev.examm.services.ClassificationService;
import com.krasimirkolchev.examm.services.TaskService;
import com.krasimirkolchev.examm.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ClassificationService classificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, ClassificationService classificationService, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.classificationService = classificationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskServiceModel addTask(TaskServiceModel taskServiceModel, UserServiceModel userServiceModel) {
        Task task = this.modelMapper.map(taskServiceModel, Task.class);
        task.setClassification(this.classificationService.getClassificationById(taskServiceModel.getClassification()));
        task.setProgress(Progress.OPEN.name());
        task.setUser(userServiceModel.getId());
        Task task1 = this.taskRepository.save(task);

        return this.modelMapper.map(task1, TaskServiceModel.class);
    }

    @Override
    public List<TaskViewModel> getAllTasks() {
        return this.taskRepository.findAll()
                .stream().map(t -> {
                    TaskViewModel taskViewModel = this.modelMapper.map(t, TaskViewModel.class);
                    taskViewModel.setUsername(this.userService.findUserById(t.getUser()).getUsername());

                    return taskViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void changeProgress(String id) {
        Task task = this.taskRepository.getOne(id);

        switch (task.getProgress()) {
            case "OPEN":
                task.setProgress(Progress.IN_PROGRESS.name());
                this.taskRepository.saveAndFlush(task);
                break;
            case "IN_PROGRESS":
                task.setProgress(Progress.COMPLETED.name());
                this.taskRepository.saveAndFlush(task);
                break;
            case "COMPLETED":
                this.taskRepository.deleteById(id);
                break;
        }

    }
}
