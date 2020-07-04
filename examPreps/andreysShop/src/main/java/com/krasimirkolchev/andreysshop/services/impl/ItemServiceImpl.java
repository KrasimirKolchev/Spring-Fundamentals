package com.krasimirkolchev.andreysshop.services.impl;

import com.krasimirkolchev.andreysshop.models.entities.Item;
import com.krasimirkolchev.andreysshop.models.serviceModels.ItemServiceModel;
import com.krasimirkolchev.andreysshop.repositories.ItemRepository;
import com.krasimirkolchev.andreysshop.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

import static com.krasimirkolchev.andreysshop.constants.Constants.*;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemServiceModel findItemById(String id) {
        return this.modelMapper.map(this.itemRepository.getOne(id), ItemServiceModel.class);
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {

        if (this.itemRepository.findByName(itemServiceModel.getName()) != null) {
            throw new EntityExistsException("Item already exist!");
        }

        Item item = this.modelMapper.map(itemServiceModel, Item.class);

        setImage(item);

        this.itemRepository.saveAndFlush(item);
    }

    @Override
    public List<ItemServiceModel> getAllItems() {
        return this.itemRepository.findAll()
                .stream()
                .map(i -> this.modelMapper.map(i, ItemServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItemById(String id) {
        this.itemRepository.deleteById(id);
    }

    //set default image depending of gender and category
    private void setImage(Item item) {
        switch (item.getGender()) {
            case MALE:
                switch (item.getClothCategory().getName()) {
                    case "Shorts" :
                        item.setImgSrc(MALE_SHORTS);
                        break;
                    case "Denim" :
                        item.setImgSrc(MALE_DENIM);
                        break;
                    case "Jacket" :
                        item.setImgSrc(MALE_JACKET);
                        break;
                    case "Shirt" :
                        item.setImgSrc(MALE_SHIRT);
                        break;
                }
                break;
            case FEMALE:
                switch (item.getClothCategory().getName()) {
                    case "Shorts" :
                        item.setImgSrc(FEMALE_SHORTS);
                        break;
                    case "Denim" :
                        item.setImgSrc(FEMALE_DENIM);
                        break;
                    case "Jacket" :
                        item.setImgSrc(FEMALE_JACKET);
                        break;
                    case "Shirt" :
                        item.setImgSrc(FEMALE_SHIRT);
                        break;
                }
                break;
        }
    }

}
