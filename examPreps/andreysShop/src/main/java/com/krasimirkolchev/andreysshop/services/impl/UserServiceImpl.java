package com.krasimirkolchev.andreysshop.services.impl;

import com.krasimirkolchev.andreysshop.models.entities.User;
import com.krasimirkolchev.andreysshop.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.andreysshop.repositories.UserRepository;
import com.krasimirkolchev.andreysshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
    public User findUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }
}
