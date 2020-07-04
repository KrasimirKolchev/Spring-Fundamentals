package com.krasimirkolchev.andreysshop.web;

import com.krasimirkolchev.andreysshop.models.serviceModels.ItemServiceModel;
import com.krasimirkolchev.andreysshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){

        if (httpSession.getAttribute("user") != null) {
            List<ItemServiceModel> items = this.itemService.getAllItems();

            model.addAttribute("items", items);
            model.addAttribute("count", items.size());
            return "home";
        }

        return "index";
    }
}
