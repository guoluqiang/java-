package com.guo.sharding.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.Date;

@Repository
public class CreateTable {

    @Value(value = "${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driver;

    @Value(value = "${spring.datasource.dynamic.datasource.master.url}")
    private String url;

    @Value(value = "${spring.datasource.dynamic.datasource.master.username}")
    private String userName;

    @Value(value = "${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    @Scheduled(cron = "0 */1 * * * ?")
    public void init() throws SQLException, ClassNotFoundException{
        Date date = new Date();
        String year = String.format("%tY", date);
        String mon = String.format("%tm",date);
        System.out.println("开始执行新建数据库了"+url+"  "+userName+"  "+password);
        //连接数据库
        Class.forName(driver);
        //测试url中是否包含useSSL字段，没有则添加设该字段且禁用
        if( url.indexOf("?") == -1 ){
            url = url + "?useSSL=false" ;
        }
        else if( url.indexOf("useSSL=false") == -1 || url.indexOf("useSSL=true") == -1 )
        {
            url = url + "&useSSL=false";
        }
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stat = conn.createStatement();
        //获取数据库表名
        String tableName ="file_test_"+year+mon;

        ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null);
        // 判断表是否存在，如果存在则什么都不做，否则创建表
        if( rs.next() ){
            System.out.println("数据库已存在");
            return;
        }
        else{

            //创建行政区划表
            String test = "CREATE TABLE "+tableName+" (";
            stat.executeUpdate(test
                    +"`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',"
                    +"`file_name` varchar(255) DEFAULT NULL COMMENT '名称',"
                    +"`create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',"
                    +"`update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',"
                    +"PRIMARY KEY (`id`),"
                    +"UNIQUE KEY `index_id` (`id`) USING BTREE COMMENT '唯一索引',"
                    +"KEY `index_test` (`file_name`,`create_time`) USING BTREE COMMENT '普通索引'"
                    +") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表';"

            );
            System.out.println("成功创建"+tableName);

        }
        // 释放资源
        stat.close();
        conn.close();
    }

}
