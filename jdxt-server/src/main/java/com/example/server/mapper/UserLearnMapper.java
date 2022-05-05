package com.example.server.mapper;

import com.example.server.pojo.UserLearn;
import org.graalvm.compiler.lir.LIRInstruction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserLearnMapper {
    List <UserLearn> finduserlearn(String uid);
    int insertuserlearn(UserLearn userLearn);

    int finduserlearncount(String uid);
}
