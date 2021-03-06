package com.krasimirkolchev.andreysshop.web;

import com.krasimirkolchev.andreysshop.models.bindingModels.UserLoginBindingModel;
import com.krasimirkolchev.andreysshop.models.bindingModels.UserRegisterBindingModel;
import com.krasimirkolchev.andreysshop.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.andreysshop.services.UserService;
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
        model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public String userRegisterConf(@Valid @ModelAttribute("userRegisterBindingModel")
                  UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            return "register";
        } else {

            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
                redirectAttributes.addFlashAttribute("passErr", true);
                return "redirect:register";
            }

            try {
                this.userService.registerUser(this.modelMapper
                        .map(userRegisterBindingModel, UserServiceModel.class));
                return "redirect:login";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
                return "redirect:register";
            }

        }
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());

        return "login";
    }

    @PostMapping("/login")
    public String userLoginConf(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            return "login";
        } else {

            try {
                UserServiceModel userServiceModel = this.userService
                        .findUserByUsername(userLoginBindingModel.getUsername());

                if (!userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                    redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                    redirectAttributes.addFlashAttribute("notFound", true);
                    return "redirect:login";
                }

                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("id", userServiceModel.getId());
                return "redirect:/";
            } catch (Exception ex){
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("notFound", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                return "redirect:login";
            }
        }

    }

    @GetMapping("/logout")
    public String userLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
