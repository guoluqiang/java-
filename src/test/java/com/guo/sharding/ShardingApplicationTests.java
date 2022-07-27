package com.guo.sharding;

import com.guo.sharding.quartz.CreateTable;
import com.guo.sharding.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;

@SpringBootTest
class ShardingApplicationTests {
    @Resource
    private TestServiceImpl testService;

    @Resource
    private CreateTable createTable;

    @Test
    void contextLoads() {
        testService.addTest();
    }

    @Test
    void application() throws SQLException, ClassNotFoundException {
        createTable.init();
    }

}
