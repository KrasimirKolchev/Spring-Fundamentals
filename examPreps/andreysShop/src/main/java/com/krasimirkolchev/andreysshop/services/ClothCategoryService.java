package com.krasimirkolchev.andreysshop.services;

import com.krasimirkolchev.andreysshop.models.entities.ClothCategory;
import com.krasimirkolchev.andreysshop.models.serviceModels.ClothCategoryServiceModel;

import java.util.List;

public interface ClothCategoryService {

    ClothCategoryServiceModel getCategoryById(String id);

    List<ClothCategoryServiceModel> getAllCategories();


}
