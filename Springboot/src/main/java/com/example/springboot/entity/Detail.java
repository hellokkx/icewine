package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@Data替换getter setter代码
@Data
public class Detail {
    private Integer did;
    private Integer oid;
    private Integer wid;
    private String name;
    private BigDecimal price;
    private Integer num;
    private BigDecimal totalprice;


}

