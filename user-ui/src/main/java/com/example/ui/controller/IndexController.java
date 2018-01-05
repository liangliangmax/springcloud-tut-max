package com.example.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {


    @RequestMapping("/student")
    public String studentIndex(){
        return "studentIndex";
    }

    @RequestMapping("/teacher")
    public String teacherIndex(){
        return "teacherIndex";
    }
}