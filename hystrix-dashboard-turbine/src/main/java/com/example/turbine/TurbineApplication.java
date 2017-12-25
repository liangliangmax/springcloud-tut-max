package com.example.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 浏览器里面请求
 * http://localhost:8763/hystrix
 * 然后输入http://localhost:8763/turbine.stream
 * 即可看到被监控的所有项目的曲线图
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class,args);
    }
}
