package com.example.server.service.impl;

import com.example.server.mapper.VideoMapper;
import com.example.server.pojo.Video;
import com.example.server.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    //查出视频文件地址
    @Override
    public List<Video> findvideo(){
        return videoMapper.findvideo();
    }
}
