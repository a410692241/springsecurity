package com.wei.config;

import com.wei.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                //指定登录页的路径
                .loginPage("/auth/require")
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/Error.html")
                .defaultSuccessUrl("/Success.html")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .and()
                .authorizeRequests()
//                .antMatchers("/hello", "/Login.html").permitAll()
                .antMatchers("/auth/require", securityProperties.getBrowser().getLoginPage()).permitAll()
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
