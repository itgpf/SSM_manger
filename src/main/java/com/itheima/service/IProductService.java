package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {
    //查询所有商品信息
    public List<Product> findAll() throws Exception;
    //添加商品信息
    void save(Product product) throws Exception;
}
