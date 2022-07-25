package com.guo.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test")
public class Test {
    private int id;
    private String fieId;
    private String value;
    private String error;
    private String createTime;
    private String updateTime;
}
