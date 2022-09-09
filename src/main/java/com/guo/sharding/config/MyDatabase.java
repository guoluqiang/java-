package com.guo.sharding.config;

import lombok.Data;

@Data
public class MyDatabase {
    private String url;
    private String userName;
    private String passWord;
    private String type;
}
