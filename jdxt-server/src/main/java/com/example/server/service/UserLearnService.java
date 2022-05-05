package com.example.server.service;

import com.example.server.mapper.UserLearnMapper;
import com.example.server.pojo.UserLearn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLearnService {
    List<UserLearn> finduserlearn(String uid);
    int insertuserlearn(UserLearn userLearn);

    int finduserlearncount(String uid);
}
