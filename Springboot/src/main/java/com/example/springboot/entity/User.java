package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//@Data替换getter setter代码
@Data
public class User {
    private Integer uid;
    private String username;
    private String pos;
    private Integer age;
    private String gen;
    private String tel;
    private String birth;
    private String ads;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
}

