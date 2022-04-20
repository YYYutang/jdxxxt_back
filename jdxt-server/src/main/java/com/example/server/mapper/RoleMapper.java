package com.example.server.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Role;

import java.util.List;
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRoles(String adminId);

}
