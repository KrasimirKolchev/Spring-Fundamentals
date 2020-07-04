package com.krasimirkolchev.andreysshop.repositories;

import com.krasimirkolchev.andreysshop.models.entities.ClothCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothCategoryRepository  extends JpaRepository<ClothCategory, String> {
}
