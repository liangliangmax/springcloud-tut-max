package com.example.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Product;
import com.example.product.service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/product/{id}")
    public String findById(@PathVariable("id") String id){
        Product product = productService.findById(id);

        return JSONObject.toJSONString(product);

    }


    @RequestMapping("/prod/{id}")
    public String selectById(@PathVariable("id") String id){
        Product product = productService.selectById(id);

        return JSONObject.toJSONString(product);
    }
}
