package com.example.server.service;


import com.example.server.pojo.ShortAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShortAnswerService {
    List<ShortAnswer> findshort(String  chapter, String section);
    int insertshort(List<ShortAnswer> shortAnswer);
}
