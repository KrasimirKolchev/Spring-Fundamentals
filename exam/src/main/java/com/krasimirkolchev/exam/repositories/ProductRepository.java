package com.krasimirkolchev.exam.repositories;

import com.krasimirkolchev.exam.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByName(String name);

}
