package com.example.userprovider.service;

import com.example.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 断路器的另一种写法，这样做的好处是可以捕获异常
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<IUserService> {
    @Override
    public IUserService create(Throwable throwable) {
        return new IUserService() {
            @Override
            public String findById(String id) {
                return "sorry "+id+" , network not worked";
            }

            @Override
            public String findByUsername(String username) {
                return "sorry "+username+" , network not worked";
            }

            @Override
            public String findALl() {
                return "sorry , network not worked";
            }

            @Override
            public int add(User user) {
                return -1;
            }

            @Override
            public String findByPage(Map<String, Object> params) {
                return "分页失败";
            }

            @Override
            public String fileUpload(MultipartFile file){
                throwable.printStackTrace();

                System.out.println("貌似出错了");
                return "貌似出错了";
            }
        };
    }
}
