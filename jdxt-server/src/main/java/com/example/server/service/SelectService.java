package com.example.server.service;

import com.example.server.pojo.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SelectService {
    List<Select> findselect(String  chapter, String section);
    int insertselect(List<Select> select);

}
