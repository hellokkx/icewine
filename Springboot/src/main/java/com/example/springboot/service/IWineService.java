package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Wine;
import com.github.pagehelper.PageInfo;

import java.util.List;

//接口
public interface IWineService {
    List<Wine>list();

    PageInfo<Wine> page(BaseRequest baseRequest);

    void save(Wine obj);

    Wine getById(Integer id);

    void update(Wine obj);

    void deleteById(Integer id);
}
