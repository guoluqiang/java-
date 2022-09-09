package com.guo.sharding.service.impl;

import com.guo.sharding.entity.Demo;
import com.guo.sharding.mapper.DemoMapper;
import com.guo.sharding.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoMapper;

    @Override
    public void saveDemo() {
        demoMapper.addDemo("demo");
    }

    @Override
    public List<Demo> queryDemo1() {
        return demoMapper.getDemo();
    }

    @Override
    public List<Demo> queryDemo2() {
        return demoMapper.getDemo();
    }
}
