package com.krasimirkolchev.heroes.services;

import com.krasimirkolchev.heroes.domain.entity.User;
import com.krasimirkolchev.heroes.domain.serviceModels.UserServiceModel;

public interface UserService {

    UserServiceModel findByUsername(String username);

    User findById(String id);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
