package com.wei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecurityBowersApplication {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/user")
    public String user() {
        return "user";
    }
    public static void main(String[] args) {
        SpringApplication.run(SecurityBowersApplication.class, args);
    }


}
