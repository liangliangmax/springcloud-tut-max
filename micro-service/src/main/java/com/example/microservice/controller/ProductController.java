package com.example.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.microservice.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @RequestMapping("/prod/{id}/{productName}")
    public String findById(@PathVariable("id") String id, @PathVariable("productName") String productName){
        Product product=new Product();

        product.setId(id);
        product.setProductName(productName);
        product.setPrice("10000");

        return JSONObject.toJSONString(product);

    }
}
