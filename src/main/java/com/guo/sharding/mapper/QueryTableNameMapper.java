package com.guo.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QueryTableNameMapper{

    List<String> tableNameList(@Param("tableName") String tableName);

    List<String> tableNames(@Param("libraryName") String libraryName,
                            @Param("tableName")String tableName);
}
