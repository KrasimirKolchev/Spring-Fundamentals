package com.westcoastdealershop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeIndex() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
