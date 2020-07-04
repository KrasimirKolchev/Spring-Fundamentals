package com.krasimirkolchev.heroes.domain.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class HeroAddBindingModel {
    private String name;
    private String heroClass;
    private Integer level;

    public HeroAddBindingModel() {
    }

    @Length(min = 2, message = "Hero name must be at least 2 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Select hero class!")
    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    @Positive(message = "Hero level cannot be below 0!")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
