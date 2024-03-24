package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Detail;
import com.example.springboot.mapper.DetailMapper;
import com.example.springboot.service.IDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//实现类
@Slf4j
@Service
public class DetailService implements IDetailService {

    @Autowired
    DetailMapper detailMapper;

    @Override
    public List<Detail> list(Integer id) {
        return detailMapper.list(id);
    }


    @Override
    public Detail getById(Integer id) {
        return detailMapper.getById(id);
    }




    @Override
    public PageInfo<Detail> page(BaseRequest baseRequest) {
        List<Detail> objs = detailMapper.listByCondition(baseRequest);

        int page = 1;
        int pageSize = 15;

        int total = objs.size();
        List<Detail> collect = objs
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        //计算总页数

        int pageSum = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        PageHelper.startPage(page, pageSize);

        PageInfo<Detail> PageInfo = new PageInfo<Detail>(collect);
        PageInfo.setTotal(total);
        PageInfo.setPages(pageSum);
        PageHelper.clearPage();
        return PageInfo;

    }



}
