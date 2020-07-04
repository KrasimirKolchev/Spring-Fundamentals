package com.krasimirkolchev.exam.services;

import com.krasimirkolchev.exam.models.serviceModels.ProductServiceModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel productServiceModel);

    ProductServiceModel findProductByName(String name);

    List<ProductServiceModel> getAllFoods();

    List<ProductServiceModel> getAllDrinks();

    List<ProductServiceModel> getAllOthers();

    List<ProductServiceModel> getAllHouseholds();

    double getTotalPrice();

    void buyProductById(String id);

    void buyAllProducts();
}
