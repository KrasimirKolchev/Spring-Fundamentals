package com.westcoastdealershop.services;

import com.westcoastdealershop.models.entities.User;
import com.westcoastdealershop.models.serviceModels.UserServiceModel;

import java.util.Collection;
import java.util.List;

public interface UserService {

    Collection<User> getAllUsers();

    User getUserById(String id);

    User getUserByUsername(String username);

    UserServiceModel registerUser(UserServiceModel user);

    User updateUser(User user);

    User deleteUser(String id);

    long getUsersCount();

    boolean userExistByUsername(String username);

    void createUsersBatch(List<User> users);

    UserServiceModel loginUser(UserServiceModel userServiceModel);
}
