package com.example.product.service.service;

import com.example.entity.Product;
import com.example.product.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    /**
     * 本方法调用传统的mybatis的接口文件
     * @param id
     * @return
     */
    @Override
    public Product findById(String id) {
        return productMapper.findById(id);
    }

    /**
     * 本方法测试tkmybatis
     * @param id
     * @return
     */
    @Override
    public Product selectById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
