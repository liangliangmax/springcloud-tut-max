package com.example.good.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "micro-service",fallback = ProductServiceHystrix.class)
public interface IProductService {

    @RequestMapping(value="/api/product/prod/{id}/{productName}",method = RequestMethod.GET)
    String findById(@RequestParam("id") String id, @RequestParam("productName") String productName);
}
