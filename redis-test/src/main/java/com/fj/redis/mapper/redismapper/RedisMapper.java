package com.fj.redis.mapper.redismapper;

import com.fj.redis.entity.CanDataDto;
import com.fj.redis.entity.TbMachineinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RedisMapper {
    /**
     * 查询农机信息
     */
    List<CanDataDto> queryMachineInfo();

    List<TbMachineinfo> queryMachineAll();
}
