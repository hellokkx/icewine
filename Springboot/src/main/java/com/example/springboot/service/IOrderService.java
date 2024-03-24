package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

//接口
public interface IOrderService {
    List<Order>list();

    PageInfo<Order> page(BaseRequest baseRequest);

    void save(Order obj);

    Order getById(Integer id);

    void update(Order obj);

    void deleteById(Integer id);

    void confirmById(Integer id);
}
