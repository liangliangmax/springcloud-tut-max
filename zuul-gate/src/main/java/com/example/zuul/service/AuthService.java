package com.example.zuul.service;

import com.example.entity.UserForAuth;
import com.example.zuul.jwt.JwtTokenUtil;
import com.example.zuul.rpc.UserRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRpc userRpc;

    @Autowired
    private JwtTokenUtil tokenUtil;


    public String login(String username, String password) {
        UserForAuth userForAuth = userRpc.login(username,password);

        String token="";
        if(userForAuth != null){
            token = tokenUtil.generateToken(userForAuth);
        }

        return token;
    }

    public String refresh(String token) {
        return tokenUtil.refreshToken(token);
    }

    public void validate(String token) {

    }

    public void invalid(String token) {

    }
}
