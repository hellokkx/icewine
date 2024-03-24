package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Detail;
import com.example.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DetailMapper {

    List<Detail> list(Integer id);
    List<Detail> listByCondition(BaseRequest userPageRequest);
    Detail getById(Integer id);

}


