package com.krasimirkolchev.heroes.services.impl;

import com.krasimirkolchev.heroes.domain.entity.User;
import com.krasimirkolchev.heroes.domain.serviceModels.UserServiceModel;
import com.krasimirkolchev.heroes.repositories.UserRepository;
import com.krasimirkolchev.heroes.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        if (this.findByUsername(userServiceModel.getUsername()) != null) {
            throw new EntityExistsException("User already exist!");
        }

        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }
}
