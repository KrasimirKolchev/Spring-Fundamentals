package com.krasimirkolchev.exam.services;

import com.krasimirkolchev.exam.models.serviceModels.UserServiceModel;

public interface UserService {

    UserServiceModel findUserByUsername(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
