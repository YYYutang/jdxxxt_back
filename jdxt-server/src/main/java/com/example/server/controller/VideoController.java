package com.example.server.controller;

import com.example.server.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@Api(tags="VideoController")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @ApiOperation(value="获取视频")
    @GetMapping("/getvideo")
    public List findvideo(Model model) {
    List<Video> video=  videoService.findvideo();
        System.out.println(videoService.findvideo());
        model.addAttribute("video", video);
        return video;
    }
}
