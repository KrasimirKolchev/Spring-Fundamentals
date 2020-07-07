package com.krasimirkolchev.exam.services;

import com.krasimirkolchev.exam.models.serviceModels.ProductServiceModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel productServiceModel);

    ProductServiceModel findProductByName(String name);

    boolean productExistByName(String name);

    List<ProductServiceModel> getProductsByCategoryName(String name);

    double getTotalPrice();

    void buyProductById(String id);

    void buyAllProducts();
}
