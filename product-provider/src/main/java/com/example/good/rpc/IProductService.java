package com.example.good.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "micro-service",fallback = ProductServiceHystrix.class)
public interface IProductService {

    @RequestMapping(value="/api/product/{id}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id);

    @RequestMapping(value="/api/prod/{id}",method = RequestMethod.GET)
    String selectById(@RequestParam("id") String id);
}
