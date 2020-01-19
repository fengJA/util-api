package com.fj.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fj.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao extends BaseMapper<User> {

}
