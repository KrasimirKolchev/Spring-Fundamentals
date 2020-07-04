package com.krasimirkolchev.heroes.web;

import com.krasimirkolchev.heroes.domain.bindingModels.HeroAddBindingModel;
import com.krasimirkolchev.heroes.domain.entity.HeroClass;
import com.krasimirkolchev.heroes.domain.serviceModels.HeroServiceModel;
import com.krasimirkolchev.heroes.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/heroes")
public class HeroController {
    private final HeroService heroService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroController(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String heroCreate(Model model) {
        model.addAttribute("heroAddBindingModel", new HeroAddBindingModel());
        model.addAttribute("heroClass", HeroClass.values());
        return "create-hero";
    }

    @PostMapping("/create")
    public String heroCreateConf(@Valid @ModelAttribute("heroAddBindingModel") HeroAddBindingModel heroAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("heroAddBindingModel", heroAddBindingModel);
            return "create-hero";
        }

        if (this.heroService.findHeroByName(heroAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("heroAddBindingModel", heroAddBindingModel);
            redirectAttributes.addFlashAttribute("heroFound", true);
            return "create-hero";
        }

        try {
            HeroServiceModel heroServiceModel = this.modelMapper.map(heroAddBindingModel, HeroServiceModel.class);
            heroServiceModel.setHeroClass(HeroClass.valueOf(heroAddBindingModel.getHeroClass()));
            this.heroService.createHero(heroServiceModel);
            return "redirect:/";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("heroAddBindingModel", heroAddBindingModel);
            return "create-hero";
        }

    }

    @GetMapping("/details/{id}")
    public String heroDetails(@PathVariable("id") String id, Model model) {

        HeroServiceModel heroServiceModel = this.modelMapper.map(this.heroService.getHeroById(id), HeroServiceModel.class);
        model.addAttribute("hero", heroServiceModel);
        return "details-hero";
    }

    @GetMapping("/delete/{id}")
    public String heroDelete(@PathVariable("id") String id, Model model) {

        HeroServiceModel heroServiceModel = this.modelMapper.map(this.heroService.getHeroById(id), HeroServiceModel.class);
        model.addAttribute("hero", heroServiceModel);
        return "delete-hero";
    }

    @PostMapping("/delete/{id}")
    public String heroDeleteConf(@PathVariable("id") String id) {

        this.heroService.deleteHeroById(id);

        return "redirect:/";
    }

}
