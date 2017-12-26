package com.example.microservice.service;


import com.example.entity.User;
import com.example.microservice.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

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
    public int add(User user) {
        return userMapper.insert(user);
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
