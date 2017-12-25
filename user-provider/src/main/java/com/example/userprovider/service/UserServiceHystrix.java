package com.example.userprovider.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements IUserService{

    @Override
    public String findById(String id, String username) {
        return "sorry "+id+" , network not worked";
    }
}
