package com.example.server.service.impl;

import com.example.server.mapper.UserLearnMapper;
import com.example.server.pojo.UserLearn;
import com.example.server.service.UserLearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLearnServiceImpl implements UserLearnService {
    @Autowired
    private UserLearnMapper userLearnMapper;
    @Override
   public List<UserLearn> finduserlearn(String uid){
        return userLearnMapper.finduserlearn(uid);
    }
    @Override
   public int insertuserlearn(UserLearn userLearn){
        return userLearnMapper.insertuserlearn(userLearn);
    }
    @Override
    public int finduserlearncount(String uid){
        return userLearnMapper.finduserlearncount(uid);
    }
}
