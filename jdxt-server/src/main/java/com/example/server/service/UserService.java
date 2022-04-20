package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserService extends IService<User> {
    /**
     * 登录返回token
     */
    RespBean login(String id, String psw, HttpServletRequest request);

    /**
     * 根据用户id获取用户
     */
    User getAdminByUserName(String id);


    List<Role> getRoles(String id);
}
