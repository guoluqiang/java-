package com.guo.sharding.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AOP 拦截类
 */
@Aspect
@Component
public class DataSourceAop {

    @Autowired
    MultDataSource multDataSource;


    @Before("execution(* com.guo.sharding.mapper..*.get*(..)) || " +
            "execution(* com.guo.sharding.mapper..*.list*(..)) ||"+
            "execution(* com.guo.sharding.mapper..*.select*(..))")
    public  void setReadDataSource(){
        DatabaseContextHolder.setDataSourceType(getReadKey());
        System.out.println("我是读");
    }

    @Before("execution(* com.guo.sharding.mapper..*.add*(..)) || " +
            "execution(* com.guo.sharding.mapper..*.update*(..)) ||"+
            "execution(* com.guo.sharding.mapper..*.insert*(..)) ||"+
            "execution(* com.guo.sharding.mapper..*.delete*(..))")
    public  void setWriteDataSource(){
        DatabaseContextHolder.setDataSourceType(getMainKey());
        System.out.println("我是写");
    }

    /**
     * 轮询方式
     */
    int m = 0;
    public String getMainKey(){
        List<String> readKeys = multDataSource.getMainKeys();
        m ++;
        m = m%readKeys.size();
        return readKeys.get( m );
    }

    int i = 0;
    public String getReadKey(){
        List<String> readKeys = multDataSource.getReadKeys();
        i ++;
        i = i%readKeys.size();
        return readKeys.get( i );
    }
}
