package com.westcoastdealershop.web;

import com.westcoastdealershop.models.bindingModels.UserAddBindingModel;
import com.westcoastdealershop.models.bindingModels.UserLoginBindingModel;
import com.westcoastdealershop.models.entities.enums.Role;
import com.westcoastdealershop.models.serviceModels.UserServiceModel;
import com.westcoastdealershop.services.UserService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String NOT_FOUND_MSG = "Invalid username or password!";
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String registerUser(Model model) {
        if (!model.containsAttribute("userAddBindingModel")) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
            model.addAttribute("selectRoles", Role.values());
        }
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerUserConf(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (userAddBindingModel.getRoles().isEmpty()) {
            bindingResult.rejectValue("roles", "error.userAddBindingModel", "Select role!");
        }

        if (this.userService.userExistByUsername(userAddBindingModel.getUsername())) {
            bindingResult.rejectValue("username", "error.userAddBindingModel", "User already exist!");
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                    , bindingResult);
            return "redirect:register";

        } else {

            try {
                UserServiceModel userServiceModel = this.modelMapper.map(userAddBindingModel, UserServiceModel.class);
                userServiceModel.setImageUrl("/img/user-avatar.svg");
                UserServiceModel user = this.userService.registerUser(userServiceModel);
                return "redirect:/";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel"
                        , bindingResult);
                return "redirect:register";
            }
        }

    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "auth-login";
    }

    @PostMapping("/login")
    public String loginUserConf(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                    , bindingResult);
            return "auth-login";
        } else {

            try {
                UserServiceModel userServiceModel = this.userService.
                        loginUser(this.modelMapper.map(userLoginBindingModel, UserServiceModel.class));

                httpSession.setAttribute("id", userServiceModel.getId());
                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("roles", userServiceModel.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()));

                return "redirect:/";
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("NotFound", NOT_FOUND_MSG);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel"
                        , bindingResult);
                return "redirect:/users/login";
            }
        }

    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession httpSession) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/users/login");
        return modelAndView;
    }

}
