package com.krasimirkolchev.exam.repositories;

import com.krasimirkolchev.exam.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByName(String name);

    boolean existsByName(String name);

    List<Product> findProductsByCategoryName(String categoryName);
}
