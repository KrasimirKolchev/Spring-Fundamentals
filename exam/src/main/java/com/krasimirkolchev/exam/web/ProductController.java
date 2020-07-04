package com.krasimirkolchev.exam.web;

import com.krasimirkolchev.exam.models.bindingModels.ProductAddBindingModel;
import com.krasimirkolchev.exam.models.serviceModels.ProductServiceModel;
import com.krasimirkolchev.exam.services.CategoryService;
import com.krasimirkolchev.exam.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String productAdd(Model model) {
        model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "product-add";
    }

    @PostMapping("/add")
    public String productAddConf(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel"
                    , bindingResult);
            return "product-add";
        }

        if (this.productService.findProductByName(productAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel"
                    , bindingResult);
            redirectAttributes.addFlashAttribute("productFound", true);
            return "redirect:add";
        }

        try {
            ProductServiceModel productServiceModel = this.modelMapper
                    .map(productAddBindingModel, ProductServiceModel.class);
            productServiceModel.setCategory(this.categoryService.getCategoryById(productAddBindingModel.getCategory()));
            this.productService.addProduct(productServiceModel);
            return "redirect:/";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel"
                    , bindingResult);
            return "redirect:add";
        }

    }

    @GetMapping("/buy/")
    public String buyProduct(@RequestParam(name = "id") String id) {
        this.productService.buyProductById(id);
        return "redirect:/";
    }

    @GetMapping("buy/all")
    public String buyAllProducts() {
        this.productService.buyAllProducts();
        return "redirect:/";
    }

}
