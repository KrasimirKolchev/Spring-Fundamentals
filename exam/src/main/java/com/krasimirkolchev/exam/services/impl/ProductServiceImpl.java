package com.krasimirkolchev.exam.services.impl;

import com.krasimirkolchev.exam.models.entities.Product;
import com.krasimirkolchev.exam.models.serviceModels.ProductServiceModel;
import com.krasimirkolchev.exam.repositories.ProductRepository;
import com.krasimirkolchev.exam.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {

        if (this.productRepository.findByName(productServiceModel.getName()) != null) {
            throw new EntityExistsException("Product already exist!");
        }

        Product product = this.modelMapper.map(productServiceModel, Product.class);

        return this.modelMapper.map(this.productRepository.save(product), ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel findProductByName(String name) {
        return this.modelMapper.map(this.productRepository.findByName(name), ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> getAllFoods() {
        return this.productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getName().equals("Food"))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> getAllDrinks() {
        return this.productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getName().equals("Drink"))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> getAllOthers() {
        return this.productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getName().equals("Other"))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> getAllHouseholds() {
        return this.productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getName().equals("Household"))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalPrice() {
        return this.productRepository.findAll()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public void buyProductById(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }


}
