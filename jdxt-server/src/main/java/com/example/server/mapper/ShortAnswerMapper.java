package com.example.server.mapper;


import com.example.server.pojo.ShortAnswer;

import java.util.List;

public interface ShortAnswerMapper {
    List<ShortAnswer> findshort(String  chapter, String section);
    int insertshort(List<ShortAnswer> shortAnswer);
}
