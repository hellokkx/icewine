package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.controller.request.WinePageRequest;
import com.example.springboot.entity.Wine;
import com.example.springboot.service.IWineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//对外--前端 传输数据
@CrossOrigin  //SpringBoot跨域问题解决
@RestController
@RequestMapping("/wine")
public class WineController {

    @Autowired
    IWineService wineService;


    @PostMapping("/save")
    public Result save(@RequestBody Wine obj){
        wineService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Wine obj){
        wineService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        wineService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Wine obj=wineService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list(){
        List<Wine>objs=wineService.list();
        return Result.success(objs);
    }

    @GetMapping("/page")
    public Result page(WinePageRequest winePageRequest){
        return Result.success(wineService.page(winePageRequest));
    }


}
