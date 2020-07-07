package com.judgever2.services.impl;

import com.judgever2.models.entities.Role;
import com.judgever2.models.serviceModels.RoleServiceModel;
import com.judgever2.repositories.RoleRepository;
import com.judgever2.services.RoleService;
import com.judgever2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void initRoles() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByAuthority(name)
                .orElse(null);
    }


}
