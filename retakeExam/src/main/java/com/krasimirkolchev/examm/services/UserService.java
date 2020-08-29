package com.krasimirkolchev.examm.services;

import com.krasimirkolchev.examm.models.serviceModels.UserServiceModel;

public interface UserService {

    UserServiceModel findUserByEmail(String email);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    boolean existByUsername(String username);

    boolean existByEmail(String email);

    UserServiceModel findUserById(String id);

}
