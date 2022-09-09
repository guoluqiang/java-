package com.guo.sharding.mapper;

import com.guo.sharding.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DemoMapper {
    void saveDemo(@Param("name") String name);

    List<Demo> getDemo();
}
