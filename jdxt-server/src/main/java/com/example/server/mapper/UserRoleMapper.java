package com.example.server.mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    Integer addAdminRole(@Param("Id") Integer Id, @Param("rids") Integer[] rids);
}
