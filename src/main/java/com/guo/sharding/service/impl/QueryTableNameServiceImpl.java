package com.guo.sharding.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.guo.sharding.mapper.QueryTableNameMapper;
import com.guo.sharding.service.QueryTableNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@DS("master")
public class QueryTableNameServiceImpl implements QueryTableNameService {

    @Resource
    private QueryTableNameMapper queryTableNameMapper;

    @Override
    public List<String> tableNameList(String tableName) {
        return queryTableNameMapper.tableNameList(tableName+"%");
    }

    @Override
    public List<String> tableNames(String libraryName, String tableName) {
        return queryTableNameMapper.tableNames(libraryName,tableName+"%");
    }
}
