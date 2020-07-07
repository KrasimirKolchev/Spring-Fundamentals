package com.judgever2.web;

import com.judgever2.models.bindingModels.RoleAddBindingModel;
import com.judgever2.models.serviceModels.RoleAddServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.services.RoleService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String rolesAdd(@AuthenticationPrincipal Principal principal, Model model) {
        UserServiceModel currentUser = this.userService.findByUsername(principal.getName());
        model.addAttribute("roleAddBindingModel", new RoleAddBindingModel());
        model.addAttribute("users", this.userService.getAllUsers()
                .stream()
                .filter(u -> !u.equals(currentUser.getUsername()))
                .collect(Collectors.toList()));

        return "role-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String rolesAddConf(@Valid @ModelAttribute("roleAddBindingModel")
                                       RoleAddBindingModel roleAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("roleAddBindingModel", roleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.roleAddBindingModel", bindingResult);
            return "role-add";
        } else {
            this.userService.addRoleToUser(this.modelMapper.map(roleAddBindingModel, RoleAddServiceModel.class));

            return "redirect:/";
        }
    }
}
