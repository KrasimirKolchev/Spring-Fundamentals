package com.judgever2.services;

import com.judgever2.models.serviceModels.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByName(String name);

}
