package com.example.microservice.service;


import com.example.entity.User;
import com.example.entity.UserForAuth;

import java.util.List;
import java.util.Map;

public interface IUserService {
    User findById(String id);

    User selectByUsername(String username);

    List<User> findAll();

    int add(User user);

    UserForAuth validate(String username,String password);

    List<User> findByPage(Map<String,Object> params);
}
