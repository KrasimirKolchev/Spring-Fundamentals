package com.krasimirkolchev.examm.web;


import com.krasimirkolchev.examm.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final TaskService taskService;

    @Autowired
    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){

        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("tasks", this.taskService.getAllTasks());
            return "home";
        }
        return "index";
    }
}
