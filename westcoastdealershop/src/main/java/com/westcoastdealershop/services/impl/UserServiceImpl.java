package com.westcoastdealershop.services.impl;

import com.westcoastdealershop.models.entities.User;
import com.westcoastdealershop.models.serviceModels.UserServiceModel;
import com.westcoastdealershop.repositories.UserRepository;
import com.westcoastdealershop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    public Collection<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID: %s doesn't exist!", id)));
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username: %s doesn't exist!", username)));
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        this.userRepository.findByUsername(userServiceModel.getUsername()).ifPresent(u -> {
            throw new EntityExistsException(String.format("User with username: %s already exist!", u.getUsername()));
        });
            User user = this.modelMapper.map(userServiceModel, User.class);

            user.setActive(true);
            user.setCreated(new Date());
            user.setModified(new Date());

            return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public User updateUser(User user) {
        user.setModified(new Date());
        return this.userRepository.save(user);
    }

    @Override
    public User deleteUser(String id) {
        User user = this.getUserById(id);
        this.userRepository.deleteById(id);
        return user;
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public void createUsersBatch(List<User> users) {
        users.forEach(user -> {
            user.setCreated(new Date());
            user.setModified(new Date());
            this.userRepository.save(user);
        });
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);

        if (user == null || !user.getPassword().equals(userServiceModel.getPassword())) {
            throw new IllegalArgumentException("Username or password doesn't match!");
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
