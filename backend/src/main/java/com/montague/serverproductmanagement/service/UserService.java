package com.montague.serverproductmanagement.service;

import com.montague.serverproductmanagement.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    // Save can be used to create a new user or update an existing one
    User updateUser(User user);

    void deleteUser(User user);

    User findByUsername(String username);

    List<User> findAllUsers();

    Long numberOfUsers();
}
