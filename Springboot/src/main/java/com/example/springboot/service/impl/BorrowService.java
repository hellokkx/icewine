package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Wine;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.WineMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


//实现类
@Slf4j
@Service
public class BorrowService implements IBorrowService {

    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    WineMapper wineMapper;

    private static final String DEFAULT_PASS="123456";
    private static final String PASS_SALT="lkx";

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        List<Borrow> objs = borrowMapper.listByCondition(baseRequest);

        int page = baseRequest.getPageNum();
        int pageSize = baseRequest.getPageSize();

        int total = objs.size();
        List<Borrow> collect = objs
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        //计算总页数

        int pageSum = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        PageHelper.startPage(page, pageSize);

        PageInfo<Borrow> PageInfo = new PageInfo<Borrow>(collect);
        PageInfo.setTotal(total);
        PageInfo.setPages(pageSum);
        PageHelper.clearPage();
        return PageInfo;

    }

    @Override
    @Transactional
    public void save(Borrow obj) {
        //校验冰酒数量是否足够
//        Wine wine=wineMapper.getByNo(obj.getBookno());
//        if(Objects.isNull(wine)){
//            throw new ServiceException("所借图书不存在");
//        }
//        Integer nums=wine.getNums();
//        if(nums<=0){
//            throw new ServiceException("图书数量不足");
//        }
//        //更新图书数量
//        wine.setNums(wine.getNums()-1);
//        wineMapper.updateById(wine);
        //新增借书记录
        obj.setCreatetime(new Date());
        borrowMapper.save(obj);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow obj) {
        obj.setUpdatetime(new Date());
        borrowMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {

        String bookno=borrowMapper.getNoById(id);
        Wine wine=wineMapper.getByNo(bookno);
        if(Objects.isNull(wine)){
            throw new ServiceException("所还冰酒不存在");
        }

        //更新图书数量
//        book.setNums(book.getNums()+1);
//        bookMapper.updateById(book);

        borrowMapper.deleteById(id);
    }


}
