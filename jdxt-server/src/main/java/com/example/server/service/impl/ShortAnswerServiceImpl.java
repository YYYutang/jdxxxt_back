package com.example.server.service.impl;


import com.example.server.mapper.ShortAnswerMapper;

import com.example.server.pojo.ShortAnswer;
import com.example.server.service.ShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShortAnswerServiceImpl implements ShortAnswerService {
    @Autowired
    private ShortAnswerMapper shortAnswerMapper;
    @Override
    public List<ShortAnswer> findshort(String chapter, String section){
        return shortAnswerMapper.findshort(chapter,section);
    }
    public int insertshort(List<ShortAnswer> shortAnswer){
        return shortAnswerMapper.insertshort(shortAnswer);
    }
}
