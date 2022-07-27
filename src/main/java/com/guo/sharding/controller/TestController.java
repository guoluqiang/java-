package com.guo.sharding.controller;

import com.guo.sharding.service.QueryTableNameService;
import com.guo.sharding.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    private QueryTableNameService queryTableNameService;

    @GetMapping(value = "/add")
    public void addOrder() {
        testService.addTest();
    }

    @PostMapping("/list/table")
    public List<String> tableNameList(@RequestParam String tableName){
        return queryTableNameService.tableNameList(tableName);
    }

    @PostMapping("/list/tables")
    public List<String> tableNames(@RequestParam String libraryName,
                                   @RequestParam String tableName){
        return queryTableNameService.tableNames(libraryName,tableName);
    }
}
