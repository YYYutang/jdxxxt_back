package com.example.server.controller;

import com.example.server.pojo.ShortAnswer;
import com.example.server.service.ShortAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="ShortAnswerController")
public class ShortAnswerController {
    @Autowired
    private ShortAnswerService shortAnswerService;
    @ApiOperation(value="获取章节简答题")
    @PostMapping("/getshort")
    public List<ShortAnswer> findshort(@RequestBody ShortAnswer shortAnswer){
        String chapter=shortAnswer.getChapter();
        String section=shortAnswer.getSection();
        return shortAnswerService.findshort(chapter,section);
    }
    @ApiOperation(value="增加章节简答题")
    @PostMapping("/insertshort")
    public int insertshort(@RequestBody List<ShortAnswer> shortAnswer){
        return shortAnswerService.insertshort(shortAnswer);
    }
}
