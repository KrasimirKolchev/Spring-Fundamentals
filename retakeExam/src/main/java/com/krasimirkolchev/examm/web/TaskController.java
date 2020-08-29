package com.krasimirkolchev.examm.web;

import com.krasimirkolchev.examm.models.bindingModels.TaskAddBindingModel;
import com.krasimirkolchev.examm.models.serviceModels.TaskServiceModel;
import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.examm.services.ClassificationService;
import com.krasimirkolchev.examm.services.TaskService;
import com.krasimirkolchev.examm.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final ClassificationService classificationService;
    private final UserService userService;
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(ClassificationService classificationService, UserService userService, TaskService taskService, ModelMapper modelMapper) {
        this.classificationService = classificationService;
        this.userService = userService;
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addTask(Model model) {
        if (!model.containsAttribute("taskAddBindingModel")) {
            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
        }
        model.addAttribute("classifications", classificationService.getAllClassifications());

        return "add-task";
    }

    @PostMapping("/add")
    public String addTaskConf(@Valid @ModelAttribute("taskAddBindingModel") TaskAddBindingModel taskAddBindingModel,
                              BindingResult result, RedirectAttributes attributes, HttpSession session) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel"
                    , result);
            return "redirect:/tasks/add";
        }

        try {
            TaskServiceModel serviceModel = this.modelMapper.map(taskAddBindingModel, TaskServiceModel.class);
            this.taskService.addTask(serviceModel, this.userService.findUserById(session.getAttribute("id").toString()));

            return "redirect:/";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            attributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel"
                    , result);
            return "redirect:/tasks/add";
        }
    }

    @PostMapping("/changeProgress/")
    public String changeProgress(@RequestParam(name = "id") String id) {
        this.taskService.changeProgress(id);
        return "redirect:/";
    }
}
