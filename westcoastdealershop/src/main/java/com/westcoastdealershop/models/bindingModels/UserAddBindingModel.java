package com.westcoastdealershop.models.bindingModels;

import com.westcoastdealershop.models.entities.enums.Role;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserAddBindingModel {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> roles;

    public UserAddBindingModel() {
    }

    public UserAddBindingModel(String username, String firstName, String lastName, String password, Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 symbols!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Length(min = 3, max = 20, message = "First name must be between 3 and 20 symbols!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Length(min = 3, max = 20, message = "Last name must be between 3 and 20 symbols!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Length(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Role must be selected!")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
