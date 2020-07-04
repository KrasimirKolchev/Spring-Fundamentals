package com.judgever2.web;

import com.judgever2.models.bindingModels.UserAddBindingModel;
import com.judgever2.models.bindingModels.UserLoginBindingModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.models.viewModels.UserProfileView;
import com.judgever2.services.HomeworkService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String USER_FOUND = "User with username %s already exists!";
    private static final String EMAIL_FOUND = "Email %s already exists!";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HomeworkService homeworkService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, HomeworkService homeworkService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.homeworkService = homeworkService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAddBindingModel", new UserAddBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                    , bindingResult);
            return "redirect:register";
        } else {

            String error = registrationErrorCheck(userAddBindingModel, bindingResult, redirectAttributes);
            if (!error.isBlank()) {
                return error;
            }

            try {
                UserServiceModel userServiceModel = this.userService.registerUser(this.modelMapper
                        .map(userAddBindingModel, UserServiceModel.class));
                return "redirect:/users/login";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                        , bindingResult);
                return "redirect:register";
            }

        }
    }

    private String registrationErrorCheck(UserAddBindingModel userAddBindingModel,
                                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.userService.findByUsername(userAddBindingModel.getUsername()) != null) {
            System.out.println(String.format(USER_FOUND, userAddBindingModel.getUsername()));
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                    , bindingResult);
            redirectAttributes.addFlashAttribute("registrationError", String
                    .format(USER_FOUND, userAddBindingModel.getUsername()));
            return "redirect:register";
        }

        if (this.userService.findUserByEmail(userAddBindingModel.getEmail()) != null) {
            System.out.println(String.format(EMAIL_FOUND, userAddBindingModel.getEmail()));
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                    , bindingResult);
            redirectAttributes.addFlashAttribute("registrationError", String
                    .format(EMAIL_FOUND, userAddBindingModel.getEmail()));
            return "redirect:register";
        }

        return "";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());

        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "login";
        } else {

            UserServiceModel userServiceModel = this.userService
                    .findByUsername(userLoginBindingModel.getUsername());

            if (userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
                redirectAttributes.addFlashAttribute("notFound", true);
                return "redirect:login";
            } else {
                httpSession.setAttribute("id", userServiceModel.getId());
                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("role", userServiceModel.getRole().getName());
                return "redirect:/";
            }

        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String userProfile(@RequestParam(name = "id") String id, Model model) {
        UserProfileView userProfileView = this.modelMapper
                .map(this.userService.findUserById(id), UserProfileView.class);
        model.addAttribute("user", userProfileView);
        model.addAttribute("homeWorks", String.join(",\n", this.homeworkService
                                                                                        .getHomeworksByUser(id)));
        return "profile";
    }
}
