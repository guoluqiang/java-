package com.guo.sharding.controller;

import com.guo.sharding.entity.Demo;
import com.guo.sharding.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping(value = "/add")
    public void saveDemo() {
        demoService.saveDemo();
    }

    @PostMapping(value = "/query1")
    public List<Demo> queryDemo1() {
        return demoService.queryDemo1();
    }

    @PostMapping(value = "/query2")
    public List<Demo> queryDemo2() {
        return demoService.queryDemo2();
    }
}
