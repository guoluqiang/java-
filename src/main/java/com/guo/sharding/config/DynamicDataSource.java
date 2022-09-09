package com.guo.sharding.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = DatabaseContextHolder.getDataSourceType();
        System.out.println("动态获取到的 数据源key == "+dataSourceType);
        return dataSourceType;
    }
}
