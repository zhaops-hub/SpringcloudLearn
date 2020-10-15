package com.zhaops.springcloudprovider.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SoYuan
 */
@RestController
@RequestMapping("/hello")
public class HelloProviderEndpoint {
    /**
     * Hello
     * @return
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name){
        return "Hello, " + name + "!";
    }
}
