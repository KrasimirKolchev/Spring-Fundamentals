package com.krasimirkolchev.linkedOut.web;

import com.krasimirkolchev.linkedOut.models.bindingModels.CompanyAddBindingModel;
import com.krasimirkolchev.linkedOut.models.serviceModels.CompanyServiceModel;
import com.krasimirkolchev.linkedOut.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String companyAdd(Model model) {
        model.addAttribute("companyAddBindingModel", new CompanyAddBindingModel());
        return "company-add";
    }

    @PostMapping("/add")
    public String companyAddConf(@Valid @ModelAttribute("companyAddBindingModel") CompanyAddBindingModel companyAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            return "company-add";
        }

        try {
            this.companyService.addCompany(this.modelMapper.map(companyAddBindingModel, CompanyServiceModel.class));
            return "redirect:/";
        } catch (EntityExistsException ex) {
            System.out.println(ex.getMessage());
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("companyFound", true);
            return "redirect:add";
        }
    }

    @GetMapping("/all")
    public String companyAll(Model model) {
        model.addAttribute("companies", this.companyService.getAllCompanies());
        return "company-all";
    }

    @GetMapping("/details/{id}")
    public String companyDetails(@PathVariable(name = "id") String id, Model model) {
        CompanyServiceModel companyServiceModel = this.modelMapper
                .map(this.companyService.findCompanyById(id), CompanyServiceModel.class);
        model.addAttribute("company", companyServiceModel);
        return "company-details";
    }

    @GetMapping("/delete/{id}")
    public String companyDelete(@PathVariable(name = "id") String id) {
        this.companyService.deleteCompanyById(id);
        return "company-all";
    }

}
