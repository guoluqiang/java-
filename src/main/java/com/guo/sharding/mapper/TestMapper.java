package com.guo.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.sharding.entity.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<Test> {
}
