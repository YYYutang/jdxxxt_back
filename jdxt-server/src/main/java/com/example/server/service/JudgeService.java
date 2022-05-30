package com.example.server.service;

import com.example.server.pojo.Judge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JudgeService {
    List<Judge> findjudge(String chapter,String section);
    int insertjudge(List<Judge> judge);

}
