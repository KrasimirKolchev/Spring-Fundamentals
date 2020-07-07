package com.judgever2.web;

import com.judgever2.models.bindingModels.UserAddBindingModel;
import com.judgever2.models.bindingModels.UserLoginBindingModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.models.viewModels.UserProfileView;
import com.judgever2.services.HomeworkService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String USER_FOUND_ERR = "User with username %s already exists!";
    private static final String EMAIL_FOUND_ERR = "Email %s already exists!";

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
        if (!model.containsAttribute("userAddBindingModel")) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.userAddBindingModel", "Passwords doesn't match!");
        }

        if (this.userService.userExistByUsername(userAddBindingModel.getUsername())) {
            bindingResult.rejectValue("username", "error.userAddBindingModel"
                    , String.format(USER_FOUND_ERR, userAddBindingModel.getUsername()));
        }

        if (this.userService.userExistByEmail(userAddBindingModel.getEmail())) {
            bindingResult.rejectValue("email", "error.userAddBindingModel"
                    , String.format(EMAIL_FOUND_ERR, userAddBindingModel.getEmail()));
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                    , bindingResult);
            return "register";
        } else {

            try {
                UserServiceModel userServiceModel = this.userService.registerUser(this.modelMapper
                        .map(userAddBindingModel, UserServiceModel.class));
                return "redirect:/users/login";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                        , bindingResult);
                return "register";
            }

        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

//    @PostMapping("/login")
//    public String loginPost(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
//                            BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//            return "login";
//        } else {
//
//            UserServiceModel userServiceModel = this.userService
//                    .findByUsername(userLoginBindingModel.getUsername());
//            this.userService.loadUserByUsername(userLoginBindingModel.getUsername());
//
//            if (userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
//                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
//                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//                redirectAttributes.addFlashAttribute("notFound", true);
//                return "redirect:login";
//            } else {
//                httpSession.setAttribute("id", userServiceModel.getId());
//                httpSession.setAttribute("user", userServiceModel);
//                httpSession.setAttribute("role", userServiceModel.getRole().getName());
//                return "redirect:/home";
//            }
//
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal Principal principal, Model model) {

        UserServiceModel user = this.modelMapper
                .map(this.userService.findByUsername(principal.getName()), UserServiceModel.class);

        UserProfileView userProfileView = this.modelMapper
                .map(user, UserProfileView.class);
        model.addAttribute("user", userProfileView);
        model.addAttribute("homeWorks", String.join(",\n", this.homeworkService
                                                                                        .getHomeworksByUser(user.getId())));
        return "profile";
    }
}
