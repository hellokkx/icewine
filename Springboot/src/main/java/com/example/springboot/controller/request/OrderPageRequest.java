package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class OrderPageRequest extends BaseRequest{
    private Integer uid;
    private Integer oid;

}
