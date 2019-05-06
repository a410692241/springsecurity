package com.wei.social.Impl;

import com.wei.bo.QQUserInfo;
import com.wei.social.QQ;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    public static final String getAccessTokenUrl = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=&client_secret=";
    public static final String getUserInfoUrl = "";

    @Override
    public QQUserInfo getQQUserInfo() {
        return null;
    }
}
