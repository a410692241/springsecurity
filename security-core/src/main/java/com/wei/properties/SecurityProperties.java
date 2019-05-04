package com.wei.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "com.wei.security.browser")
public class SecurityProperties {
    private BrowserProperties browserProperties = new BrowserProperties();


}
