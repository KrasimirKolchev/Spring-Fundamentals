package com.judgever2.services.impl;

import com.judgever2.models.entities.Comment;
import com.judgever2.models.entities.User;
import com.judgever2.models.serviceModels.RoleAddServiceModel;
import com.judgever2.models.serviceModels.UserServiceModel;
import com.judgever2.repositories.UserRepository;
import com.judgever2.services.RoleService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found!");
        }

        return user;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(Set.of(this.roleService.findByName("ROLE_ADMIN")));
        }
        else {
            userServiceModel.setAuthorities(Set.of(this.roleService.findByName("ROLE_USER")));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

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

        user.setAuthorities(Set.of(this.roleService.findByName(roleAddServiceModel.getRole())));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(String id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public boolean userExistByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public String getPrincipalId(Principal principal) {
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userRepository.findByUsername(principal.getName()), UserServiceModel.class);
        return userServiceModel.getId();
    }

    @Override
    public long getStudentsCount() {
//        return this.userRepository.findAll()
//                .stream()
//                .filter(u -> u.getAuthorities().getAuthority().equals("USER"))
//                .count();
        return this.userRepository.findAll().size();

    }

    @Override
    public double getAverageGrades() {
        return this.userRepository.findAll()
                .stream()
//                .filter(u -> u.getRole().getAuthority().equals("USER"))
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
