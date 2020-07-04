package com.krasimirkolchev.heroes.services.impl;

import com.krasimirkolchev.heroes.domain.entity.Hero;
import com.krasimirkolchev.heroes.domain.serviceModels.HeroServiceModel;
import com.krasimirkolchev.heroes.repositories.HeroRepository;
import com.krasimirkolchev.heroes.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HeroServiceModel> getAllHeroes() {
        return this.heroRepository.findAll()
                .stream()
                .map(hero -> this.modelMapper.map(hero, HeroServiceModel.class))
                .sorted(Comparator.comparingInt(HeroServiceModel::getLevel))
                .collect(Collectors.toList());
    }

    @Override
    public Hero getHeroById(String id) {
        return this.heroRepository.getOne(id);
    }

    @Override
    public HeroServiceModel createHero(HeroServiceModel heroServiceModel) {
        Hero hero = this.modelMapper.map(heroServiceModel, Hero.class);

        return this.modelMapper.map(this.heroRepository.save(hero), HeroServiceModel.class);
    }

    @Override
    public void deleteHeroById(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public Hero findHeroByName(String name) {
        return this.heroRepository.findByName(name);
    }
}
