package com.krasimirkolchev.examm.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginBindingModel {
    private String email;
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email should contain '@'")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 symbols!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
