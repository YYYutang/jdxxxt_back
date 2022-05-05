package com.example.server.controller;

import com.example.server.pojo.UserLearn;
import com.example.server.pojo.Video;
import com.example.server.service.UserLearnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="UserLearnController")
public class UserLearnController {
    @Autowired
    private UserLearnService userLearnService;

    @ApiOperation(value="获取用户学习记录")
    @PostMapping("/getuserlearn")
    public List<UserLearn> finduserlearn(@RequestBody String uid){
       List<UserLearn> userlearn = userLearnService.finduserlearn(uid);
       return userlearn;
    }
    @ApiOperation(value="获取用户学习记录个数")
    @PostMapping(value="/getuserlearncount",produces ="application/json;charset=UTF-8")
    public int finuserlearncount(@RequestBody String uid){
        int count=userLearnService.finduserlearncount(uid);
        System.out.println("输入参数："+uid);
        System.out.println("输出参数"+count);
        return count;
    }
    @ApiOperation(value="增加用户学习记录")
    @PostMapping("/adduserlearn")
    public int insertuserlearn(String uid,String l_chapter,String l_section){
        UserLearn userLearn= new UserLearn();
        userLearn.setUid(uid);
        userLearn.setL_chapter(l_chapter);
        userLearn.setL_section(l_section);
        return userLearnService.insertuserlearn(userLearn);
    }
}
