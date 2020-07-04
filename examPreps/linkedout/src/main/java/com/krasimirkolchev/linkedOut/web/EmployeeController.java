package com.krasimirkolchev.linkedOut.web;

import com.krasimirkolchev.linkedOut.models.bindingModels.EmployeeAddBindingModel;
import com.krasimirkolchev.linkedOut.models.entity.EducationLevel;
import com.krasimirkolchev.linkedOut.models.serviceModels.CompanyServiceModel;
import com.krasimirkolchev.linkedOut.models.serviceModels.EmployeeServiceModel;
import com.krasimirkolchev.linkedOut.services.CompanyService;
import com.krasimirkolchev.linkedOut.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employeeAddBindingModel", new EmployeeAddBindingModel());
        model.addAttribute("companies", this.companyService.getAllCompanies());
        model.addAttribute("education", EducationLevel.values());
        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployeeConf(@Valid @ModelAttribute("employeeAddBindingModel") EmployeeAddBindingModel employeeAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            return "employee-add";
        }

        EmployeeServiceModel employeeServiceModel = this.modelMapper
                .map(employeeAddBindingModel, EmployeeServiceModel.class);
        CompanyServiceModel companyServiceModel = this.modelMapper
                .map(this.companyService.findCompanyById(employeeAddBindingModel.getCompany()), CompanyServiceModel.class);

        employeeServiceModel.setBirthDate(LocalDate.parse(employeeAddBindingModel.getBirthDate()));
        employeeServiceModel.setCompany(companyServiceModel);
        employeeServiceModel.setEducationLevel(EducationLevel.valueOf(employeeAddBindingModel.getEducationLevel()));

        this.employeeService.addEmployee(employeeServiceModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String allEmployees(Model model) {
        model.addAttribute("employees", this.employeeService.getAllEmployees());
        return "employee-all";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(@PathVariable(name = "id") String id, Model model) {
        EmployeeServiceModel employeeServiceModel = this.modelMapper
                .map(this.employeeService.findEmployeeById(id), EmployeeServiceModel.class);
        model.addAttribute("employee", employeeServiceModel);
        return "employee-details";
    }

    @GetMapping("/delete/{id}")
    public String employeeDelete(@PathVariable(name = "id") String id) {
        this.employeeService.deleteEmployeeById(id);
        return "employee-all";
    }
}
