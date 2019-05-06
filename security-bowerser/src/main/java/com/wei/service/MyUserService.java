package com.wei.service;

import com.wei.bo.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("用户名:" + userName);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(userName);
        com.wei.bo.User user = userService.selectByExample(example).stream().findFirst().orElse(null);
        if (user == null || user.getPassword() == null) {
            return null;
        }
        return new User(userName,user.getPassword(),
                //此处可以实现自己的业务逻辑,从而实现用户被锁,用户登录超时,用户权限组
                true,true ,true,true,
                //赋予的角色必须是以大写ROLE开头
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin"));
    }
}
