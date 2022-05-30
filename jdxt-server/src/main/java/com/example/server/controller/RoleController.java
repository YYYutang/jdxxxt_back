package com.example.server.controller;

import com.example.server.pojo.Role;
import com.example.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RestController

public class RoleController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="获取用户角色")
    @PostMapping(value="/getrole",produces ="application/json;charset=UTF-8")
   public List<Role> getRoles(@RequestBody String adminId){
        String userId=adminId.replace("\"","");
        List<Role> role=userService.getRoles(userId);
        return role;
    }

}