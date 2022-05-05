package com.example.server.config.security;


import com.example.server.pojo.User;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter{
        @Autowired
        private UserService userService;
        @Autowired
        private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
        @Autowired
        private  RestfulAccessDeniedHandler restfulAccessDeniedHandler;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        }
        @Override
        public void configure(WebSecurity web)throws Exception{
            web.ignoring().antMatchers(
                    "/websocket/**",
                    "/login",
                    "/**.html",
                    "/login/**",
                    "//hello/**",
                    "/register/**",
                    "/logout/**",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/fonts/**",
                    "favicon.ico",
                    "/doc.html",                    // 放行 swagger 资源
                    "/webjars/**",                  // 放行 swagger 资源
                    "/swagger-resources/**",        // 放行 swagger 资源
                    "/v2/api-docs/**",              // 放行 swagger 资源
                    "/captcha",      // 验证码接口
                    "/ws/**",
                    "/video/**"
            );
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            //使用JWT，不需要csrf
            http.csrf()
                    .disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .headers()
                    .cacheControl();
            http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            http.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthorizationEntryPoint);

        }
        @Override
        @Bean
        public UserDetailsService userDetailsService(){
            return name-> {
                User user=userService.getAdminByUserName(name);
                if(null!=user){
                    //这里有错
                    user.setRoles(userService.getRoles(user.getName()));
                    return user;
                }
                throw new UsernameNotFoundException("用户名或密码不正确");
            };
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        @Bean
        public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
            return new JwtAuthencationTokenFilter();
        }
    }

