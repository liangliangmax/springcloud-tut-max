package com.example.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
public class UserUiBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserUiBootstrap.class,args);
    }
}
