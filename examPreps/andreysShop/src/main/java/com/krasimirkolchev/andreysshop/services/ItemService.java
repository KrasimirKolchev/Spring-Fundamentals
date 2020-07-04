package com.krasimirkolchev.andreysshop.services;

import com.krasimirkolchev.andreysshop.models.serviceModels.ItemServiceModel;

import java.util.List;

public interface ItemService {

    ItemServiceModel findItemById(String id);

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemServiceModel> getAllItems();

    void deleteItemById(String id);
}
