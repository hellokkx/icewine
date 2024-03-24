package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Detail;
import com.github.pagehelper.PageInfo;

import java.util.List;

//接口
public interface IDetailService {
    List<Detail>list(Integer id);

    PageInfo<Detail> page(BaseRequest baseRequest);

    Detail getById(Integer id);

}
