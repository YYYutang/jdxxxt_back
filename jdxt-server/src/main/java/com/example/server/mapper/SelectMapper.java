package com.example.server.mapper;

import com.example.server.pojo.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectMapper {
    List<Select> findselect(String  chapter, String section);
    int insertselect(List<Select> select);
}
