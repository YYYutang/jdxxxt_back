package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.example.server.config.CustomAuthorityDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="id")
    private String id;
    @ApiModelProperty(value="密码")
    private String psw;
    @ApiModelProperty(value="姓名")
    private String name;
    @ApiModelProperty(value = "是否启用")
    @Getter(AccessLevel.NONE) // 不需要生成 get 方法，防止与 UserDetails 重写的 isEnabled 冲突
    private Boolean enabled;
    @ApiModelProperty(value="邮箱")
    private String email;
    @ApiModelProperty(value="年级")
    private String grade;
    @ApiModelProperty(value="班级")
    private String classes;
    @ApiModelProperty(value="头像")
    private String avatar;
    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;
    @Override
    @JsonDeserialize(using = CustomAuthorityDeserialize.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(roles);
        List<SimpleGrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;


    }

    @Override
    public String getPassword() {
        return this.psw;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
