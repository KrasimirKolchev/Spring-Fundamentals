package com.krasimirkolchev.exam.services.impl;

import com.krasimirkolchev.exam.models.entities.Category;
import com.krasimirkolchev.exam.models.serviceModels.CategoryServiceModel;
import com.krasimirkolchev.exam.repositories.CategoryRepository;
import com.krasimirkolchev.exam.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void initClothCategories() {
        if (this.categoryRepository.count() == 0) {
            //Food, Drink, Household, Other -> initialize categories
            Category food = new Category("Food");
            Category drink = new Category("Drink");
            Category household = new Category("Household");
            Category other = new Category("Other");

            List<Category> categories = List.of(food, drink, household, other);

            this.categoryRepository.saveAll(categories);
        }
    }

    @Override
    public List<CategoryServiceModel> getAllCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryById(String id) {
        return this.categoryRepository.getOne(id);
    }
}
