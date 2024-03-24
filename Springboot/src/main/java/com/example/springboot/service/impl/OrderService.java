package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//实现类
@Slf4j
@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderMapper orderMapper;
    UserService userService;

    private static final String DEFAULT_PASS="123456";
    private static final String PASS_SALT="lkx";

    @Override
    public List<Order> list() {
        return orderMapper.list();
    }

    @Override
    public PageInfo<Order> page(BaseRequest baseRequest) {
        List<Order> objs = orderMapper.listByCondition(baseRequest);

        int page = baseRequest.getPageNum();
        int pageSize = baseRequest.getPageSize();

        int total = objs.size();
        List<Order> collect = objs
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        //计算总页数

        int pageSum = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        PageHelper.startPage(page, pageSize);

        PageInfo<Order> PageInfo = new PageInfo<Order>(collect);
        PageInfo.setTotal(total);
        PageInfo.setPages(pageSum);
        PageHelper.clearPage();
        return PageInfo;

    }

    @Override
    public void save(Order obj) {
        obj.setTime(new Date());
        orderMapper.save(obj);
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.getById(id);
    }

    @Override
    public void update(Order obj) {
//        obj.setTime(new Date());
        orderMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    @Override
    public void confirmById(Integer id) {
        orderMapper.confirmById(id);
    }


}
