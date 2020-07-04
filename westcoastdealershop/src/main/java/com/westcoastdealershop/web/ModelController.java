package com.westcoastdealershop.web;

import com.westcoastdealershop.models.bindingModels.ModelAddBindingModel;
import com.westcoastdealershop.models.entities.enums.Category;
import com.westcoastdealershop.models.serviceModels.ModelServiceModel;
import com.westcoastdealershop.services.BrandService;
import com.westcoastdealershop.services.ModelService;
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

import javax.validation.Valid;

@Controller
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService, ModelMapper modelMapper) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("modelAddBindingModel", new ModelAddBindingModel());
        model.addAttribute("brands", this.brandService.getAllBrands());
        model.addAttribute("categories", Category.values());
        return "model-add";
    }

    @PostMapping("/add")
    public String addModelConf(@Valid @ModelAttribute("modelAddBindingModel") ModelAddBindingModel modelAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
            return "model-add";
        } else {

            try {
                this.modelService.createModel(this.modelMapper
                        .map(modelAddBindingModel, ModelServiceModel.class));
                return "home";
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
                redirectAttributes.addFlashAttribute("modelFound", true);
                return "model-add";
            }
        }

    }
}
