package com.zhaops.consumer.service;

import org.springframework.stereotype.Component;

/**
 * @author SoYuan
 */
@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello, " + name + ", I'm fallback!";
    }
}
