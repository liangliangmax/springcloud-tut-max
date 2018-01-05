//package com.example.userprovider.service;
//
//import com.example.entity.User;
//import com.example.entity.UserForAuth;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//
//@Component
//public class UserServiceHystrix implements IUserService{
//
//    @Override
//    public String findById(String id) {
//        return "sorry "+id+" , network not worked";
//    }
//
//    @Override
//    public String findByUsername(String username) {
//        return "sorry "+username+" , network not worked";
//    }
//
//    @Override
//    public String findALl() {
//        return "sorry , network not worked";
//    }
//
//    @Override
//    public int add(User user) {
//        return -1;
//    }
//
//    @Override
//    public String findByPage(Map<String, Object> params) {
//        return "分页失败";
//    }
//
//    @Override
//    public UserForAuth validate(UserForAuth userForAuth) {
//        return null;
//    }
//
//    @Override
//    public String fileUpload(MultipartFile file){
//
//        System.out.println("貌似出错了");
//        return "貌似出错了";
//    }
//}
