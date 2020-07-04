package com.krasimirkolchev.andreysshop.repositories;

import com.krasimirkolchev.andreysshop.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Item findByName(String name);
}
