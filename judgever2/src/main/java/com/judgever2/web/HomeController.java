package com.judgever2.web;

import com.judgever2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("studentsCount", this.userService.getStudentsCount());
            model.addAttribute("averageGrades", this.userService.getAverageGrades());

            return "home";
        }

        return "index";
    }

}
