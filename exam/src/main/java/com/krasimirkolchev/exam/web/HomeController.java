package com.krasimirkolchev.exam.web;

import com.krasimirkolchev.exam.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){

        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("foods", this.productService.getProductsByCategoryName("Food"));
            model.addAttribute("drinks", this.productService.getProductsByCategoryName("Drink"));
            model.addAttribute("others", this.productService.getProductsByCategoryName("Other"));
            model.addAttribute("households", this.productService.getProductsByCategoryName("Household"));
            model.addAttribute("totalPrice", this.productService.getTotalPrice());
            return "home";
        }
        return "index";
    }
}
