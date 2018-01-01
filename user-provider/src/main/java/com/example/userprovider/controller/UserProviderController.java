package com.example.userprovider.controller;

import com.example.entity.User;
import com.example.userprovider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Exception;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class UserProviderController {

    @SuppressWarnings("")
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

    /**
     * 这里的user可以自动映射
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public int add(User user){
        return userService.add(user);

    }

    /**
     * 这里的map必须使用@RequestParam去修饰一下，否则接受不到
     * @param params
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public String findByPage(@RequestParam Map<String,Object> params){
        return userService.findByPage(params);

    }

    /**
     * 本例子中上传文件采用前后台分离的模式上传，前台发起ajax请求，将文件上传路径指向本controller
     * 在参数列表中写上MultipartFile 即可接收到参数
     * @param file
     * @return
     */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public String fileUpload(MultipartFile file){

        System.out.println(file.getOriginalFilename());

        String filePath="/Users/liang/Desktop/files";

        try {
            //file.transferTo(new File(filePath+"/"+file.getOriginalFilename()));


            //做实验，看看feign怎么往后传文件
            String result = userService.fileUpload(file);

            System.out.println("result = "+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
