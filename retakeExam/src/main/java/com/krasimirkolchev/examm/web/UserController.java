package com.krasimirkolchev.examm.web;

import com.krasimirkolchev.examm.models.bindingModels.UserLoginBindingModel;
import com.krasimirkolchev.examm.models.bindingModels.UserRegisterBindingModel;
import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.examm.services.UserService;
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

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String userRegister(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String userRegisterConf(@Valid @ModelAttribute("userRegisterBindingModel")
                                           UserRegisterBindingModel userRegisterBindingModel, BindingResult result, RedirectAttributes attributes) {

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            result.rejectValue("password", "error.userRegisterBindingModel", "Passwords Doesn't match!");
        }

        if (this.userService.existByUsername(userRegisterBindingModel.getUsername())) {
            result.rejectValue("username", "error.userRegisterBindingModel", "Username exist!");
        }

        if (this.userService.existByEmail(userRegisterBindingModel.getEmail())) {
            result.rejectValue("email", "error.userRegisterBindingModel", "Email exist!");
        }


        if (result.hasErrors()) {
            attributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel"
                    , result);

            return "redirect:/users/register";
        }

        try {
            this.userService.registerUser(this.modelMapper
                    .map(userRegisterBindingModel, UserServiceModel.class));
            return "redirect:login";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            attributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel"
                    , result);
            return "redirect:/users/register";
        }


    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String userLoginConf(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                    , bindingResult);
            return "redirect:/users/login";
        } else {

            try {
                UserServiceModel userServiceModel = this.userService
                        .findUserByEmail(userLoginBindingModel.getEmail());

                if (!userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                    redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                            , bindingResult);
                    redirectAttributes.addFlashAttribute("loginErr", true);
                    return "redirect:/users/login";
                }

                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("id", userServiceModel.getId());
                return "redirect:/";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("loginErr", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                        , bindingResult);
                return "redirect:/users/login";
            }
        }

    }

    @GetMapping("/logout")
    public String userLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
