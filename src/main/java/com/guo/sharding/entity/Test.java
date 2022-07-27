package com.guo.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("file_test")
public class Test {
    private int id;
    private String fileName;
    private String createTime;
    private String updateTime;
}
