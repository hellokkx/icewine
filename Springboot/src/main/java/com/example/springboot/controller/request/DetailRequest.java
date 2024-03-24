package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class DetailRequest extends BaseRequest{
    private Integer wid;
    private Integer oid;

}
