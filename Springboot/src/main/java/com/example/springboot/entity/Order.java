package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@Data替换getter setter代码
@Data
public class Order {
    private Integer oid;
    private String pay;
    private String price;
    private Integer uid;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

//    //树形结构
//    private List<Order> children;
}

