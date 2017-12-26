package com.example.microservice.service;


import com.example.entity.User;

import java.util.List;

public interface IUserService {
    User findById(String id);

    User selectByUsername(String username);

    List<User> findAll();

    int add(User user);
}
