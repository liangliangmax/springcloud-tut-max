package com.example.good.controller;

import com.example.good.rpc.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class GoodProviderController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/{id}/{productName}")
    public String findById(@PathVariable("id") String id, @PathVariable("productName") String productName){

        return productService.findById(id,productName);

    }


}
