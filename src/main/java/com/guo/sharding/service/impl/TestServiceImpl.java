package com.guo.sharding.service.impl;

import com.guo.sharding.dao.TestMapper;
import com.guo.sharding.entity.Test;
import com.guo.sharding.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public void addTest() {

        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Test t = new Test();
            t.setId(i);
            t.setFieId("i->"+i);
            t.setValue("ceshi->"+i);
            t.setError("error->"+i);
            t.setCreateTime(df.format(date));
            t.setUpdateTime(df.format(new Date()));
            testMapper.insert(t);
        }
    }
}
