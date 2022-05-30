package com.example.server.controller;

import com.example.server.pojo.Judge;
import com.example.server.service.JudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="JudgeController")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @ApiOperation(value="获取章节判读题")
    @PostMapping("/getjudge")
    public List<Judge> findjudge(@RequestBody Judge judge){
        String chapter=judge.getChapter();
        String section=judge.getSection();
        return judgeService.findjudge(chapter,section);
    }
    @ApiOperation(value="增加章节判断题")
    @PostMapping("/insertjudge")
    public int insertselect(@RequestBody List<Judge> judge){
        return judgeService.insertjudge(judge);
    }

}
