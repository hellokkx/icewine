package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Wine;
import com.example.springboot.mapper.WineMapper;
import com.example.springboot.service.IWineService;
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
public class WineService implements IWineService {

    @Autowired
    WineMapper wineMapper;

    private static final String DEFAULT_PASS="123456";
    private static final String PASS_SALT="lkx";

    @Override
    public List<Wine> list() {
        return wineMapper.list();
    }

    @Override
    public PageInfo<Wine> page(BaseRequest baseRequest) {
        List<Wine> objs = wineMapper.listByCondition(baseRequest);

        int page = baseRequest.getPageNum();
        int pageSize = baseRequest.getPageSize();

        int total = objs.size();
        List<Wine> collect = objs
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        //计算总页数

        int pageSum = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        PageHelper.startPage(page, pageSize);

        PageInfo<Wine> PageInfo = new PageInfo<Wine>(collect);
        PageInfo.setTotal(total);
        PageInfo.setPages(pageSum);
        PageHelper.clearPage();
        return PageInfo;

    }

    @Override
    public void save(Wine obj) {
//        obj.setCategory(category(obj.getCategories()));
        obj.setSold("0");
        obj.setTime(new Date());
        obj.setUpdatetime(new Date());
        wineMapper.save(obj);
    }

    @Override
    public Wine getById(Integer id) {
        return wineMapper.getById(id);
    }

    @Override
    public void update(Wine obj) {
//        obj.setCategory(category(obj.getCategories()));
        obj.setUpdatetime(new Date());
        wineMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        wineMapper.deleteById(id);
    }


    private String category(List<String> categories){
        StringBuilder sb=new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v->sb.append(v).append(" > "));
            return sb.toString().substring(0,sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }


}
