package com.judgever2.web;

import com.judgever2.models.bindingModels.ExerciseAddBindingModel;
import com.judgever2.models.serviceModels.ExerciseServiceModel;
import com.judgever2.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/exercise")
public class ExerciseController {
    private static final String EXERCISE_NAME_ERR = "Exercise with name: %s already exist!";

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addExercise(Model model) {
        if (model.containsAttribute("exerciseAddBindingModel")) {
            model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addExerciseConf(@Valid @ModelAttribute("exerciseAddBindingModel")
                ExerciseAddBindingModel exerciseAddBindingModel, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {

        if (this.exerciseService.existByExerciseName(exerciseAddBindingModel.getName())) {
            bindingResult.rejectValue("name", "error.exerciseAddBindingModel"
                    , String.format(EXERCISE_NAME_ERR, exerciseAddBindingModel.getName()));
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel"
                    , bindingResult);
            return "exercise-add";
        } else {
            try {
                ExerciseServiceModel exerciseServiceModel = this.exerciseService
                        .addExercise(this.modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel"
                        , bindingResult);
            }

            return "redirect:/";
        }

    }


}
