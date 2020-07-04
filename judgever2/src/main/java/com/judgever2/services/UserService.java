package com.judgever2.services;

import com.judgever2.models.entities.User;
import com.judgever2.models.serviceModels.RoleAddServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;

import java.util.List;

public interface UserService {

    User findUserById(String id);

    User findUserByEmail(String email);

    UserServiceModel findByUsername(String name);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<String> getAllUsers();

    void addRoleToUser(RoleAddServiceModel roleAddServiceModel);

    long getStudentsCount();

    double getAverageGrades();

}
