package com.example.microservice.service;


import com.example.entity.User;
import com.example.entity.UserForAuth;
import com.example.microservice.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //使用tkmybatis
    @Override
    public User findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //使用自定义mapper
    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    @Transactional
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public UserForAuth validate(String username,String password){
        User user = this.selectByUsername(username);
        UserForAuth userForAuth = new UserForAuth();

        if(encoder.matches(password,user.getPassword())){

            Set<String> role = new HashSet<>();
            role.add("s");
            userForAuth.setId(user.getId());
            userForAuth.setUsername(user.getUsername());
            userForAuth.setPassword(user.getPassword());
            userForAuth.setAccountNonExpired(true);
            userForAuth.setAccountNonLocked(true);
            userForAuth.setEnabled(true);
            userForAuth.setAuthorities(role);
        }

        return userForAuth;

    }

    @Override
    public List<User> findByPage(Map<String, Object> params) {

        int pageNum=Integer.parseInt(params.get("pageNum")+"");
        int pageSize=Integer.parseInt(params.get("pageSize")+"");

        PageHelper.startPage(pageNum,pageSize);

        List<User> list=userMapper.selectAll();

        return list;
    }
}
