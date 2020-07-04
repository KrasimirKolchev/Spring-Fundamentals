package com.krasimirkolchev.heroes.services;

import com.krasimirkolchev.heroes.domain.entity.Hero;
import com.krasimirkolchev.heroes.domain.serviceModels.HeroServiceModel;

import java.util.List;

public interface HeroService {

    List<HeroServiceModel> getAllHeroes();

    Hero getHeroById(String id);

    HeroServiceModel createHero(HeroServiceModel heroServiceModel);

    void deleteHeroById(String id);

    Hero findHeroByName(String name);
}
