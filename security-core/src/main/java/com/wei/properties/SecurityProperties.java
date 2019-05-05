package com.wei.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//配置文件中是com.wei.security.browser.loginPage,等同于当前类对象是security对象,browser是当前类的属性,loginPage是browser的一个属性
@ConfigurationProperties(prefix = "com.wei.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
