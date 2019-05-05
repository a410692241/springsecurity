package com.wei.controller;

import com.wei.bo.Response;
import com.wei.bo.User;
import com.wei.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class BrowserController {

    private HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
    private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();


    @RequestMapping("/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response require(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = httpSessionRequestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的请求是:" + redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                try {
                    defaultRedirectStrategy.sendRedirect(request,response, redirectUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Response data = new Response("访问的服务需要身份验证,请引导到登录界面!");
        return data;
    }
}
