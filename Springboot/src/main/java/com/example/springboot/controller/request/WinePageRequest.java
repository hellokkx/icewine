package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class WinePageRequest extends BaseRequest{
    private String winename;
    private Integer wid;
}
