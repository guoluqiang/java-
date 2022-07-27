package com.guo.sharding.service;

import java.util.List;

public interface QueryTableNameService {
    List<String> tableNameList(String tableName);

    List<String> tableNames(String libraryName, String tableName);
}
