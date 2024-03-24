package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.request.DetailRequest;
import com.example.springboot.controller.request.WinePageRequest;
import com.example.springboot.entity.Detail;
import com.example.springboot.entity.Wine;
import com.example.springboot.service.IDetailService;
import com.example.springboot.service.IWineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//对外--前端 传输数据
@CrossOrigin  //SpringBoot跨域问题解决
@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    IDetailService detailService;


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Detail obj=detailService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list/{id}")
    public Result list(@PathVariable Integer id){
        List<Detail>objs=detailService.list(id);
        return Result.success(objs);
    }

    @GetMapping("/page")
    public Result page(DetailRequest detailRequest){
        return Result.success(detailService.page(detailRequest));
    }


}
