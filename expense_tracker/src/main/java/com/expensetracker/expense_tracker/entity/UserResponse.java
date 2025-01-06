package com.expensetracker.expense_tracker.entity;

import java.util.Collection;
import java.util.List;

public class UserResponse {
    private String name;
    private String email;
    private String token;
    private List<Role> roles;

    public UserResponse() {
    }

    public UserResponse(String name, String email, String token, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
