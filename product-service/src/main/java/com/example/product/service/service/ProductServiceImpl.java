package com.example.product.service.service;

import com.example.entity.Product;
import com.example.product.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(String id) {
        return productMapper.findById(id);
    }
}
