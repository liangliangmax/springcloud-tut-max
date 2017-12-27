package com.example.product.service.mapper;

import com.example.entity.Product;
import com.example.product.service.config.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product>{

    Product findById(String id);

}
