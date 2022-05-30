package com.example.server.mapper;

import com.example.server.pojo.Judge;


import java.util.List;

public interface JudgeMapper {
    List<Judge> findjudge(String  chapter, String section);
    int insertjudge(List<Judge> judge);
}
