package com.judgever2.models.bindingModels;

import javax.validation.constraints.NotNull;

public class RoleAddBindingModel {
    private String username;
    private String role;

    public RoleAddBindingModel() {
    }

    @NotNull(message = "Select username!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Select Role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
