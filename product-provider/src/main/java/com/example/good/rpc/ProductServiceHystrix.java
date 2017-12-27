package com.example.good.rpc;

import org.springframework.stereotype.Component;

@Component
public class ProductServiceHystrix implements IProductService{
    @Override
    public String findById(String id) {
        return "wa oh,the network not work";
    }
}
