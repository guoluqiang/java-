package com.guo.sharding.service;

import com.guo.sharding.entity.Demo;

import java.util.List;

public interface DemoService {
    void saveDemo();

    List<Demo> queryDemo1();

    List<Demo> queryDemo2();
}
