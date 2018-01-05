package com.example.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/student")
    public String studentLogin(){

        return "studentLogin";
    }

    @RequestMapping("/teacher")
    public String teacherLogin(){

        return "teacherLogin";
    }

}
