package com.example.product.service.mapper;

import com.example.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    Product findById(String id);

}
