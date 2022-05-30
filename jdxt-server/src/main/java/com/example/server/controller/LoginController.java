package com.example.server.controller;

import com.example.server.pojo.User;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.UserLoginParam;
import com.example.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

@Api(tags="LoginController")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getId(), userLoginParam.getPsw(),userLoginParam.getCode(),request);
    }
    @ApiOperation(value="获取当前登陆用户的信息")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal){
        if(null==principal){
            return null;
        }
        System.out.println("进入user/info");
        String name=principal.getName();
        User user=userService.getAdminByUserName(name);
        user.setPsw(null);
        user.setRoles(userService.getRoles(user.getName()));
        return user;
    }
    @ApiOperation(value="退出登录")
    @PostMapping("/logout")
    public RespBean logout(){

        return RespBean.success("注销成功！");
    }
}
