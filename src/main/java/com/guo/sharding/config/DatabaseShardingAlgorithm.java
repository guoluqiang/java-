package com.guo.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        String db_name = preciseShardingValue.getLogicTableName(); // t_session_detail
        try {
            // 获取到分片键中的值（create_time）, 重改表名
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(preciseShardingValue.getValue()));
            String year = String.format("%tY", date);
            String mon = String.format("%tm",date);
            db_name = db_name+"_"+year+mon;

            return db_name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String each : collection) {
            System.out.println("db:" + each);
            if (each.equals(db_name)) {
                return each;
            }
        }

        throw new IllegalArgumentException();
    }

}
