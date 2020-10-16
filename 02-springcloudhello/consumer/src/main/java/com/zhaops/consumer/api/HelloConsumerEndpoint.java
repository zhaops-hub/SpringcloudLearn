package com.zhaops.consumer.api;

import com.zhaops.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SoYuan
 */
@RestController
@RequestMapping("/hello")
public class HelloConsumerEndpoint {
    @Autowired
    private HelloService helloService;

    /**
     * Hello
     * @return
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name){
        return this.helloService.hello(name);
    }
}
