package com.judgever2.services.impl;

import com.judgever2.models.entities.Comment;
import com.judgever2.models.entities.Role;
import com.judgever2.models.entities.User;
import com.judgever2.models.serviceModels.RoleAddServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.repositories.UserRepository;
import com.judgever2.services.RoleService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        if (this.userRepository.count() == 0) {
            userServiceModel.setRole(this.roleService.findByName("ADMIN"));
        }
        else {
            userServiceModel.setRole(this.roleService.findByName("USER"));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String name) {

        return this.userRepository.findByUsername(name)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(RoleAddServiceModel roleAddServiceModel) {
        User user = this.userRepository.findByUsername(roleAddServiceModel.getUsername()).orElse(null);

        if (!user.getRole().getName().equals(roleAddServiceModel.getRole())) {
            Role role = this.modelMapper
                    .map(this.roleService.findByName(roleAddServiceModel.getRole()), Role.class);

            user.setRole(role);
        }

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(String id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public long getStudentsCount() {
        return this.userRepository.findAll()
                .stream()
                .filter(u -> u.getRole().getName().equals("USER"))
                .count();
    }

    @Override
    public double getAverageGrades() {
        return this.userRepository.findAll()
                .stream().filter(u -> u.getRole().getName().equals("USER"))
                .mapToDouble(this::getStudentAvGrade)
                .average().orElse(0.0);
    }

    private double getStudentAvGrade(User user) {
        return user.getComments()
                .stream()
                .mapToDouble(Comment::getScore)
                .average().orElse(0.0);
    }
}
