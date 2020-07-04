package com.krasimirkolchev.heroes.domain.serviceModels;

import com.krasimirkolchev.heroes.domain.entity.HeroClass;

public class HeroServiceModel extends BaseServiceModel {
    private String name;
    private HeroClass heroClass;
    private Integer level;

    public HeroServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
