package com.judgever2.web;

import com.judgever2.models.bindingModels.CommentAddBindingModel;
import com.judgever2.models.bindingModels.HomeworkAddBindingModel;
import com.judgever2.models.serviceModels.CommentServiceModel;
import com.judgever2.models.serviceModels.ExerciseServiceModel;
import com.judgever2.models.serviceModels.HomeworkServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.services.CommentService;
import com.judgever2.services.ExerciseService;
import com.judgever2.services.HomeworkService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService, CommentService commentService, UserService userService, ModelMapper modelMapper) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addHomework(Model model) {
        model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
        model.addAttribute("exercises", this.exerciseService.getAllEx());
        return "homework-add";
    }

    @PostMapping("/add")
    public String addHomeworkConf(@Valid @ModelAttribute("homeworkAddBindingModel")
                HomeworkAddBindingModel homeworkAddBindingModel, BindingResult bindingResult,
                                        HttpSession httpSession, RedirectAttributes redirectAttributes) {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
            return "homework-add";
        } else {

            if (checkIfLate(homeworkAddBindingModel.getExercise())) {
                redirectAttributes.addFlashAttribute("late", true);
                redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
                return "redirect:add";
            }

            HomeworkServiceModel homeworkServiceModel = this.modelMapper
                    .map(homeworkAddBindingModel, HomeworkServiceModel.class);
            homeworkServiceModel.setAuthor(httpSession.getAttribute("id").toString());
            homeworkServiceModel.setAddedOn(LocalDateTime.now());
            homeworkServiceModel.setExercise(this.modelMapper
                    .map(this.exerciseService
                            .getById(homeworkAddBindingModel.getExercise()), ExerciseServiceModel.class));
            this.homeworkService.addHomework(homeworkServiceModel);

            return "redirect:/";
        }

    }

    private boolean checkIfLate(String exerciseId) {
        return LocalDateTime.now().isAfter(this.exerciseService.getById(exerciseId).getDueDate());
    }

    @GetMapping("/check")
    public String checkHomework(Model model) {
        model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        model.addAttribute("homework", this.homeworkService.getHomeworkToCheck());
        return "homework-check";
    }

    @PostMapping("/check")
    public String checkHomeworkConf(@Valid @ModelAttribute("commentAddBindingModel") CommentAddBindingModel commentAddBindingModel,
                  BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
            return "homework-check";
        }

        CommentServiceModel commentServiceModel = this.modelMapper.map(commentAddBindingModel, CommentServiceModel.class);
        commentServiceModel.setAuthor(this.modelMapper.map(this.userService
                .findUserById(httpSession.getAttribute("id").toString()), UserServiceModel.class));
        commentServiceModel.setHomework(this.homeworkService.getHomeworkById(commentAddBindingModel.getHomeworkId()));
        this.commentService.addComment(commentServiceModel);

        return "redirect:/";
    }
}
