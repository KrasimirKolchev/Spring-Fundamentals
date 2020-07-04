package com.krasimirkolchev.heroes.repositories;

import com.krasimirkolchev.heroes.domain.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {

    Hero findByName(String name);
}
