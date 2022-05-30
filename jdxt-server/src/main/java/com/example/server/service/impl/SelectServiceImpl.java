package com.example.server.service.impl;

import com.example.server.mapper.SelectMapper;
import com.example.server.pojo.Select;
import com.example.server.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelectServiceImpl implements SelectService {
    @Autowired
    private SelectMapper selectMapper;
    @Override
   public List<Select> findselect(String  chapter, String section){
        return selectMapper.findselect(chapter,section);
    }
    @Override
    public int insertselect(List<Select> select){
        return selectMapper.insertselect(select);
    }
}
