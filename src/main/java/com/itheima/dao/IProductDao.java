package com.itheima.dao;


import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;
    //查询所有产品的信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
     @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productStatus,productDesc) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productStatus},#{productDesc})")
    void save(Product product);
}
