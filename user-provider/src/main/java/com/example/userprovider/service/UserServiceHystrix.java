package com.example.userprovider.service;

import com.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements IUserService{

    @Override
    public String findById(String id) {
        return "sorry "+id+" , network not worked";
    }

    @Override
    public String findByUsername(String username) {
        return "sorry "+username+" , network not worked";
    }

    @Override
    public String findALl() {
        return "sorry , network not worked";
    }

    @Override
    public int add(User user) {
        return -1;
    }
}
