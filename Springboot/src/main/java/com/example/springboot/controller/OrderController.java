package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.request.OrderPageRequest;
import com.example.springboot.entity.Order;
import com.example.springboot.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//对外--前端 传输数据
@CrossOrigin  //SpringBoot跨域问题解决
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;


    @PostMapping("/save")
    public Result save(@RequestBody Order obj){
        obj.setTime(new Date());
        obj.setStatus("未完成");
        orderService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Order obj){
        orderService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        orderService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Order obj=orderService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list(){
        List<Order>objs=orderService.list();
        return Result.success(objs);
    }

    @PostMapping("/confirm/{id}")
    public Result confirm(@PathVariable Integer id){
        orderService.confirmById(id);
        return Result.success();
    }

//    @GetMapping("/tree")
//    public Result tree(){
//        List<Order>list=orderService.list();
//
//        //对list操作
//        List<Category>treeList=list.stream()
//                .filter(v ->v.getPid()==null)
//                .collect(Collectors.toList());//第一层
//        递归实现多层children
//
//        return Result.success(createTree(null,list));
//    }
//    private List<Order>createTree(Integer pid, List<Order> categories){
//        List<Order>treeList=new ArrayList<>();
//        for(Order category:categories){
//            if(pid==null){
//                if(category.getPid()==null){
//                    treeList.add(category);
//                    category.setChildren(createTree(category.getId(),categories));
//                }
//            }
//            else if(pid.equals(category.getPid())){
//                treeList.add(category);
//                category.setChildren(createTree(category.getId(),categories));
//            }
//            if(CollUtil.isEmpty(category.getChildren())){
//                category.setChildren(null);
//            }
//        }
//        return treeList;
//    }

    @GetMapping("/page")
    public Result page(OrderPageRequest orderPageRequest){
        return Result.success(orderService.page(orderPageRequest));
    }

}
