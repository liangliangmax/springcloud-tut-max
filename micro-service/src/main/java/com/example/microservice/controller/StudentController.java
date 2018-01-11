package com.example.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.entity.UserForAuth;
import com.example.microservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Exception;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable("id")  String id){

        System.out.println("开始查询");

        User user= userService.findById(id);

        return JSONObject.toJSONString(user);

    }

    @RequestMapping(value = "/student/username/{username}",method = RequestMethod.GET)
    public String findByUsername(@PathVariable("username") String username){

        User user=userService.selectByUsername(username);

        return JSONObject.toJSONString(user);

    }

    @RequestMapping(value = "/student/all",method = RequestMethod.GET)
    public String findAll(){

        List<User> list = userService.findAll();

        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
    public int add(@RequestBody User user){
        System.out.println("add 方法");
        return userService.add(user);
    }

    @RequestMapping(value = "/student/page",method = RequestMethod.POST)
    public String findByPage(@RequestBody Map<String,Object> params){

        List<User> list = userService.findByPage(params);
        return JSONObject.toJSONString(list);
    }

    @RequestMapping(value = "/student/validate",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserForAuth validate(@RequestParam("username")String username,@RequestParam("password")String password){

        UserForAuth auth = userService.validate(username,password);
        return auth;
    }


    /**
     * 这里接受文件参数什么都不需要标注就可以接收到
     * @param file
     * @return
     */
    @RequestMapping(value = "/student/fileUpload",method = RequestMethod.POST)
    public String fileUpload(MultipartFile file){

        String filePath="/Users/liang/Desktop/files";

        try {
            file.transferTo(new File(filePath+"/"+file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 默认hystrix超时时间为1s
     * @return
     */
    @RequestMapping(value = "/student/timeout",method = RequestMethod.GET)
    public String testTimeout(){
        System.out.println(new Date());
        try {

            int i=0;
            while(true){
                i++;
                System.out.println("i= "+i);
                Thread.sleep(1000);
                if(i==6){
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
}
