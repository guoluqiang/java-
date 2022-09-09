package com.guo.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@MapperScan(basePackages = "com.guo.sharding.mapper")
@PropertySource(value = "classpath:multidatabase.properties", encoding = "utf-8")
@ConfigurationProperties("my")
@Data
public class MultDataSource {

    public static final String MAIN = "main";
    public static final String READ = "read";


    public List<String> mainKeys = new ArrayList<>();
    public List<String> readKeys = new ArrayList<>();

    @Value("${my.datasource.driver}")
    private String driver;

    /**
     *  读取配置文件获取。
     */
    private List<MyDatabase> datasource;

    public DruidDataSource getDataSource(MyDatabase database) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(database.getUrl());
        druidDataSource.setUsername(database.getUserName());
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setPassword(database.getPassWord());
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setMinIdle(8);
        return druidDataSource;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (int i = 0; i < datasource.size(); i++) {
            String type = datasource.get(i).getType();
            DruidDataSource dataSource = getDataSource(datasource.get(i));
            if (MAIN.equals(type)) {
                mainKeys.add(MAIN + i);
                targetDataSources.put(MAIN + i, dataSource);
            } else {
                readKeys.add(READ + i);
                targetDataSources.put(READ + i, dataSource);
            }
        }

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为myTestDbDataSource
        dataSource.setDefaultTargetDataSource(targetDataSources.get(mainKeys.get(0)));
        return dataSource;
    }
}
