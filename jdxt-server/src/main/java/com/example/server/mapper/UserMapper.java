package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    List<User> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
