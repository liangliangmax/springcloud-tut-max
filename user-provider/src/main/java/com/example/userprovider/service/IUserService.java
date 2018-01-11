package com.example.userprovider.service;

import com.example.entity.User;
import com.example.entity.UserForAuth;
import com.example.userprovider.config.FeignMultipartSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 这里既可以写fallback=***，也可以写fallbackFactory=**
 * 但是fallbackFactory得写法可以报错，
 */
/*fallback = UserServiceHystrix.class*/
//上传文件可以吧下面这个加在后面，但是就不能传递对象了
/*,configuration = FeignMultipartSupportConfig.class*/
@FeignClient(value = "micro-service",fallbackFactory = UserServiceFallbackFactory.class)
public interface IUserService {

    @RequestMapping(value="/api/student/{id}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id);

    @RequestMapping(value = "/api/student/username/{username}",method = RequestMethod.GET)
    String findByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/api/student/all",method = RequestMethod.GET)
    String findALl();

    @RequestMapping(value = "/api/student/add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    int add(@RequestBody User user);

    @RequestMapping(value = "/api/student/page",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findByPage(@RequestBody Map<String,Object> params);


    @RequestMapping(value = "/api/student/validate",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserForAuth validate(@RequestParam("username")String username,@RequestParam("password")String password);


    /**
     * 上传文件现在不好用，要不只能上传文件，但是不能传递对象，要不能传递对象不能上传文件，
     * 这个和FeignSpringFormEncoder有关系，但是不知道怎么改
     *
     * 这里必须是@RequestPart，不能使@RequestParam,否则会报错
     * @param file
     * @return
     */
    @RequestMapping(value = "/api/student/fileUpload",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/api/student/timeout",method = RequestMethod.GET)
    String testTimeout();

}
