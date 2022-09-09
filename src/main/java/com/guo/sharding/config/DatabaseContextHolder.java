package com.guo.sharding.config;

public class DatabaseContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }
    public static String getDataSourceType() {
        return contextHolder.get();
    }
}
