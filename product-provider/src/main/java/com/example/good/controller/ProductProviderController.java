package com.example.good.controller;

import com.example.good.rpc.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductProviderController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") String id){

        return productService.findById(id);

    }


    @RequestMapping("/jar/{id}")
    public String selectById(@PathVariable("id") String id){

        return productService.selectById(id);

    }
}
