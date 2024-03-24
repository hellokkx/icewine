package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Wine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WineMapper {

    List<Wine> list();
    List<Wine> listByCondition(BaseRequest userPageRequest);

    void save(Wine obj);

    Wine getById(Integer id);

    void updateById(Wine obj);

    void deleteById(Integer id);

    Wine getByNo(String bookno);

}


