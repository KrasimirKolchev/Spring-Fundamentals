package com.krasimirkolchev.exam.services;

import com.krasimirkolchev.exam.models.entities.Category;
import com.krasimirkolchev.exam.models.serviceModels.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    List<CategoryServiceModel> getAllCategories();

    Category getCategoryById(String id);
}
