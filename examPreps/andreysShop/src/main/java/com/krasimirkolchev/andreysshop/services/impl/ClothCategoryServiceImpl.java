package com.krasimirkolchev.andreysshop.services.impl;

import com.krasimirkolchev.andreysshop.models.entities.ClothCategory;
import com.krasimirkolchev.andreysshop.models.serviceModels.ClothCategoryServiceModel;
import com.krasimirkolchev.andreysshop.repositories.ClothCategoryRepository;
import com.krasimirkolchev.andreysshop.services.ClothCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothCategoryServiceImpl implements ClothCategoryService {
    private final ClothCategoryRepository clothCategoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClothCategoryServiceImpl(ClothCategoryRepository clothCategoryRepository, ModelMapper modelMapper) {
        this.clothCategoryRepository = clothCategoryRepository;
        this.modelMapper = modelMapper;
    }


    @PostConstruct
    public void initClothCategories() {
        if (this.clothCategoryRepository.count() == 0) {
            //Shirt, Denim, Shorts or Jacket -> initialize categories
            ClothCategory shirt = new ClothCategory("Shirt", "shirts");
            ClothCategory denim = new ClothCategory("Denim", "denim");
            ClothCategory shorts = new ClothCategory("Shorts", "shorts");
            ClothCategory jacket = new ClothCategory("Jacket", "jacket");

            List<ClothCategory> categories = List.of(shirt, denim, shorts, jacket);

            this.clothCategoryRepository.saveAll(categories);
        }
    }

    @Override
    public ClothCategoryServiceModel getCategoryById(String id) {
        return this.modelMapper.map(this.clothCategoryRepository.getOne(id), ClothCategoryServiceModel.class);
    }

    @Override
    public List<ClothCategoryServiceModel> getAllCategories() {
        return this.clothCategoryRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, ClothCategoryServiceModel.class))
                .collect(Collectors.toList());
    }
}
