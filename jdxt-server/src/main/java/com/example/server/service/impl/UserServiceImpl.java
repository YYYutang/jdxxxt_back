package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.config.security.JwtTokenUtil;
import com.example.server.mapper.RoleMapper;
import com.example.server.mapper.UserRoleMapper;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.pojo.User;
import com.example.server.mapper.UserMapper;
import com.example.server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author long
 * @since 2022-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService; // 权限框架的

    @Autowired
    private PasswordEncoder passwordEncoder; // 安全框架-密码加密解密

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value ("${jwt.tokenHead}")
    private String tokenHead; // token 头部信息
    @Override
    public RespBean login(String username, String password,String code, HttpServletRequest request)
    {
// 校验验证码
        String captcha = (String) request.getSession().getAttribute("captcah");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        // 登录

        UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 调用权限框架方法获取用户名
        // 参数：第一个用户传过来的密码，第二个从 userDetails 中获取的

        if (null == userDetails || !passwordEncoder.matches( password,userDetails.getPassword())) {

            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员！");
        }
        // 自己补充其它判断：账号是否存在......

        // 更新 security 登录用户对象，设置到全局
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                , null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 登录成功，生成 token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead",tokenHead);
        tokenMap.put("token",token);
        return RespBean.success("登录成功", tokenMap);
    }

    @Override
    public List<Role> getRoles(String adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public User getAdminByUserName(String name){

        return userMapper.selectOne(new QueryWrapper<User>().eq("name",name));
    }

    @Override
    public RespBean updateUserPassword(String oldPass, String pass, String userId) {
       User user = baseMapper.selectById(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 比对密码，判断旧密码是否正确
        if (encoder.matches(oldPass, user.getPassword())) {
            // 设置密码，并加密
            user.setPsw(encoder.encode(pass));
            int result = baseMapper.updateById(user);
            if (1 == result) {
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }
    @Override
    public  RespBean updateUserAvatar(String url, String id, Authentication authentication){
    User user=userMapper.selectById(id);
    user.setAvatar(url);
    int i=userMapper.updateById(user);
    if(i==1){
        User principal=(User)authentication.getPrincipal();
        principal.setAvatar(url);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),authentication.getAuthorities()));
        return RespBean.success("更新成功",url);
    }
        return RespBean.error("更新失败");
    }
}
