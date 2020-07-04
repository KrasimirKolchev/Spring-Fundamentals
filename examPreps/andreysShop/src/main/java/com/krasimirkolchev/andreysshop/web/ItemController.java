package com.krasimirkolchev.andreysshop.web;

import com.krasimirkolchev.andreysshop.models.bindingModels.ItemAddBindingModel;
import com.krasimirkolchev.andreysshop.models.entities.Gender;
import com.krasimirkolchev.andreysshop.models.serviceModels.ItemServiceModel;
import com.krasimirkolchev.andreysshop.services.ClothCategoryService;
import com.krasimirkolchev.andreysshop.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ClothCategoryService clothCategoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, ClothCategoryService clothCategoryService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.clothCategoryService = clothCategoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        model.addAttribute("clothCategories", this.clothCategoryService.getAllCategories());
        model.addAttribute("gender", Gender.values());
        return "add-item";
    }

    @PostMapping("/add")
    public String addItemConf(@Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println("aaa");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            return "add-item";
        } else {
            ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);
            itemServiceModel.setClothCategoryServiceModel(this.clothCategoryService
                    .getCategoryById(itemAddBindingModel.getCategory()));
            itemServiceModel.setGender(Gender.valueOf(itemAddBindingModel.getGender()));

            try {
                this.itemService.addItem(itemServiceModel);
                return "redirect:/";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
                redirectAttributes.addFlashAttribute("itemFound", true);
                return "redirect:add";
            }
        }
    }

    @GetMapping("details/{id}")
    public String itemDetails(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("item", this.itemService.findItemById(id));
        return "details-item";
    }

    @GetMapping("delete/{id}")
    public String itemDelete(@PathVariable(name = "id") String id) {
        this.itemService.deleteItemById(id);
        return "redirect:/";
    }

}
