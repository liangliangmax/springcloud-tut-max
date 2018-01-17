package com.example.ui.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/fileupload")
    public String fileUpload(){
        return "index";
    }


    @RequestMapping("/student")
    public String studentIndex(){
        return "/token/studentIndex";
    }

    @RequestMapping("/teacher")
    public String teacherIndex(){
        return "teacherIndex";
    }


    //打开主页
    @RequestMapping("/main")
    public String studentMain(){
        return "studentMain";
    }

    //button1事件，
    @RequestMapping("/button1")
    public String button1(){
        return "button1";
    }

    //button2事件
    @RequestMapping("/button2")
    public String button2(){
        return "button2";
    }

    //模拟button2打开的页面进行数据请求
    @RequestMapping("/button2/data")
    @ResponseBody
    public String data(){
        Map map=new HashMap<>();

        map.put("value1","10");
        map.put("value2","20");
        map.put("value3","30");
        map.put("value4","40");

        return JSONObject.toJSONString(map);

    }

}