package com.westcoastdealershop.web;

import com.westcoastdealershop.models.bindingModels.OfferAddBindingModel;
import com.westcoastdealershop.models.entities.enums.VehCondition;
import com.westcoastdealershop.models.entities.enums.EngineType;
import com.westcoastdealershop.models.entities.enums.TransmissionType;
import com.westcoastdealershop.models.serviceModels.OfferServiceModel;
import com.westcoastdealershop.services.BrandService;
import com.westcoastdealershop.services.OfferService;
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
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
        model.addAttribute("brands", this.brandService.getAllBrands());
        model.addAttribute("engines", EngineType.values());
        model.addAttribute("condition", VehCondition.values());
        model.addAttribute("transmissions", TransmissionType.values());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOfferConf(@Valid @ModelAttribute("offerAddBindingModel") OfferAddBindingModel offerAddBindingModel,
                BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            return "offer-add";
        } else {
            String sessionUserId = httpSession.getAttribute("id").toString();
            offerAddBindingModel.setSellerId(sessionUserId);
            try {
                this.offerService.createOffer(offerAddBindingModel);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
                return "offer-add";
            }

            return "home";
        }
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOffers());

        return "offers";
    }

    @GetMapping("/details/{id}")
    public String offerDetails(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("offer", this.modelMapper
                .map(this.offerService.getOfferById(id), OfferServiceModel.class));

        return "details";
    }

    @GetMapping("/delete/{id}")
    public String offerDelete(@PathVariable(name = "id") String id) {
        this.offerService.deleteOffer(id);

        return "offers";
    }

}
