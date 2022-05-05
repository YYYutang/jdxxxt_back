package com.example.server.mapper;


import com.example.server.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
    List<Video> findvideo();
}
