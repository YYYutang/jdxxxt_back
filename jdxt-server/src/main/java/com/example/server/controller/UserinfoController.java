package com.example.server.controller;

import com.example.server.pojo.RespBean;
import com.example.server.pojo.User;
import com.example.server.service.UserService;
import com.example.server.utils.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RestController
@Api(tags="UserinfoController")
public class UserinfoController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="更新当前用户信息")
    @PutMapping("/user/info")
    public RespBean updateUser(@RequestBody User user, Authentication authentication){
        if(userService.updateById(user)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null, authentication.getAuthorities()));
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
    @ApiOperation(value = "更新用户密码")
    @PutMapping("/user/pass")
    public RespBean updateUserPassword(@RequestBody Map<String, Object>info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
       String userId = (String) info.get("userId");
        return userService.updateUserPassword(oldPass,pass,userId);
    }

    @ApiOperation(value="更新用户头像")
    @PostMapping("/user/avatar")
    public RespBean updateUserAvatar(MultipartFile file,String id,Authentication authentication){
        //获取文件上传地址
        String[] uploadPath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl() + uploadPath[0] + "/" + uploadPath[1];
        return userService.updateUserAvatar(url,id,authentication);
    }
}
