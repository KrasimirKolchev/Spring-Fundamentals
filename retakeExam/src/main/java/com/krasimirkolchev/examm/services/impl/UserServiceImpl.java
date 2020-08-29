package com.krasimirkolchev.examm.services.impl;

import com.krasimirkolchev.examm.models.entities.Task;
import com.krasimirkolchev.examm.models.entities.User;
import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;
import com.krasimirkolchev.examm.repositories.UserRepository;
import com.krasimirkolchev.examm.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
    public UserServiceModel findUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public boolean existByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public UserServiceModel findUserById(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found!"));

        return this.modelMapper.map(user, UserServiceModel.class);
    }

}
