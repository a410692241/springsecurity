package com.wei.config;

import com.wei.handler.AuthenticationFair;
import com.wei.handler.AuthenticationSuccess;
import com.wei.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccess authenticationSuccess;
    @Autowired
    private AuthenticationFair authenticationFair;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                //指定登录页的接口/路径
                .loginPage("/auth/require")
                //指定登录表单提交的action,可以对应到userServiceDetail的业务处理
                .loginProcessingUrl("/authentication/form")
                //验证失败的url
//                .failureUrl("/Error.html")
                //也可以通过提供登录成功handler,当登录成功时候返回合适的json字符串
//                .failureUrl(securityProperties.getBrowser().getFairUrl())
                .failureHandler(authenticationFair)
                //验证成功的url
//                .defaultSuccessUrl("/Success.html")
//                .defaultSuccessUrl(securityProperties.getBrowser().getSuccessUrl())
                .successHandler(authenticationSuccess)
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .and()
                .authorizeRequests()
//                .antMatchers("/hello", "/Login.html").permitAll()
                //从配置文件中提取配置,提供一个登录页url
                .antMatchers("/auth/require", securityProperties.getBrowser().getSuccessUrl(),securityProperties.getBrowser().getFairUrl(),securityProperties.getBrowser().getLoginPage()).permitAll()
                //这一句意味着所有的hello请求必须要有admin的角色权限才可以访问
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated()
                //指定自定义form表单请求的路径
                //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
                .and()
                .csrf().disable();
    }

    /**
     * @return 配置密码的加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
