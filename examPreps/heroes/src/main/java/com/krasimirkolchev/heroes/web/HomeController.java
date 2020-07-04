package com.krasimirkolchev.heroes.web;

import com.krasimirkolchev.heroes.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final HeroService heroService;

    @Autowired
    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("heroes", this.heroService.getAllHeroes());
            return "home";
        }

        return "index";
    }
}
