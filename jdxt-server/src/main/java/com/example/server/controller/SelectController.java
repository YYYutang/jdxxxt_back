package com.example.server.controller;


import com.example.server.mapper.SelectMapper;
import com.example.server.pojo.Select;
import com.example.server.service.SelectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="SelectController")
public class SelectController {
    @Autowired
 private SelectService selectService;
    @ApiOperation(value="获取章节选择题")
    @PostMapping("/getselect")
    public List<Select> findselect(@RequestBody Select select){
        String chapter=select.getChapter();
        String section=select.getSection();
        return selectService.findselect(chapter,section);
    }
    @ApiOperation(value="增加章节选择题")
    @PostMapping("/insertselect")
    public int insertselect(@RequestBody List<Select> select){
        System.out.println(select);
        return selectService.insertselect(select);
    }
}
