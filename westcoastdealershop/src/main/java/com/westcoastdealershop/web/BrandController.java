package com.westcoastdealershop.web;

import com.westcoastdealershop.models.bindingModels.BrandAddBindingModel;
import com.westcoastdealershop.models.serviceModels.BrandServiceModel;
import com.westcoastdealershop.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addBrand(Model model) {
        if (!model.containsAttribute("brandAddBindingModel")) {
            model.addAttribute("brandAddBindingModel", new BrandAddBindingModel());
        }
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrandConf(@Valid @ModelAttribute("brandAddBindingModel") BrandAddBindingModel brandAddBindingModel,
                BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (this.brandService.brandExistByName(brandAddBindingModel.getName())) {
            bindingResult.rejectValue("name", "error.brandAddBindingModel", "Brand already exist!");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandAddBindingModel", brandAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandAddBindingModel"
                    , bindingResult);
            return "brand-add";
        } else {

            try {
                this.brandService.createBrand(this.modelMapper
                        .map(brandAddBindingModel, BrandServiceModel.class));
                return "home";
            } catch (EntityExistsException ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("brandAddBindingModel", brandAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandAddBindingModel"
                        , bindingResult);
                return "brand-add";
            }
        }
    }

    @GetMapping("/brands")
    public String allBrands(Model model) {
        if (!model.containsAttribute("brands")) {
            model.addAttribute("brands", this.brandService.getAllBrands());
        }
        return "brands";
    }
}
