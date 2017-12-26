package com.example.userprovider.controller;

import com.example.entity.User;
import com.example.userprovider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class UserProviderController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable("id") String id){

        return userService.findById(id);
    }

    @RequestMapping(value = "/username/{username}",method = RequestMethod.GET)
    public String findByUsername(@PathVariable("username") String username){

        return userService.findByUsername(username);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String findAll(){
        return userService.findALl();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int add(User user){
        return userService.add(user);

    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public String findByPage(@RequestParam Map<String,Object> params){
        return userService.findByPage(params);

    }
}
