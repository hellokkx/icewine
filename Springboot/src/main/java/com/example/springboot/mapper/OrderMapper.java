package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> list();
    List<Order> listByCondition(BaseRequest userPageRequest);

    void save(Order obj);

    Order getById(Integer id);

    void updateById(Order obj);

    void deleteById(Integer id);


    void confirmById(Integer id);
}


