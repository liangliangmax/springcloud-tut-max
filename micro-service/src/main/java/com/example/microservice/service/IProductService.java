package com.example.microservice.service;


import com.example.entity.Product;

public interface IProductService {

    Product findById(String id);

    Product selectById(String id);
}
