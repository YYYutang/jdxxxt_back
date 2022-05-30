package com.example.server.service.impl;

import com.example.server.mapper.JudgeMapper;
import com.example.server.pojo.Judge;
import com.example.server.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;
    @Override
    public List<Judge> findjudge(String chapter, String section){
        return judgeMapper.findjudge(chapter,section);
    }
    public int insertjudge(List<Judge> judge){
        return judgeMapper.insertjudge(judge);
    }
}
