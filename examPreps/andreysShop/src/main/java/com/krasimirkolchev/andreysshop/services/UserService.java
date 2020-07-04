package com.krasimirkolchev.andreysshop.services;

import com.krasimirkolchev.andreysshop.models.entities.User;
import com.krasimirkolchev.andreysshop.models.serviceModels.UserServiceModel;

public interface UserService {

    User findUserById(String id);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
