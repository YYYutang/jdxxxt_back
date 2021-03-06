package com.example.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain=true)
    @ApiModel(value="UserLogin对象",description = "")
    public class UserLoginParam{
        @ApiModelProperty(value="密码",required = true)
        private String psw;
        @ApiModelProperty(value="id",required = true)
        private String id;
        @ApiModelProperty(value="验证码",required = true)
        private String code;
    }

