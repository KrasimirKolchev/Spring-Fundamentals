package com.westcoastdealershop.repositories;

import com.westcoastdealershop.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    Model findByName(String name);
}
