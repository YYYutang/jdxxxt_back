package com.example.server.service.impl;

import com.example.server.entity.User;
import com.example.server.mapper.UserMapper;
import com.example.server.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author long
 * @since 2022-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
