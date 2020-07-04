package com.krasimirkolchev.heroes.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Validation validation() {
        return new Validation();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
