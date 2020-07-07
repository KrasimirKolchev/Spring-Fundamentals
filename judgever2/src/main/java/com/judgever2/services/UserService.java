package com.judgever2.services;

import com.judgever2.models.entities.User;
import com.judgever2.models.serviceModels.RoleAddServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(String id);

    boolean userExistByEmail(String email);

    boolean userExistByUsername(String username);

    String getPrincipalId(Principal principal);

    UserServiceModel findByUsername(String name);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<String> getAllUsers();

    void addRoleToUser(RoleAddServiceModel roleAddServiceModel);

    long getStudentsCount();

    double getAverageGrades();

}
