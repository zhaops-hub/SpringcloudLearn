package com.zhaops.productservice.product.services;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.zhaops.productservice.product.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;

/**
 * @author SoYuan
 */
public class HelloServiceCommand extends HystrixCommand<UserDto> {
    private RestTemplate restTemplate;
    private String name;
    private Long id;

    public HelloServiceCommand(String name, Long id, RestTemplate restTemplate) {
        super(new HystrixCommandGroupKey() {
            @Override
            public String name() {
                return name;
            }
        });

        this.id = id;
        this.name = name;
        this.restTemplate = restTemplate;
    }

    @Override
    protected UserDto run() throws Exception {
        UserDto userDto = this.restTemplate.getForEntity("http://USERSERVICE/users/{id}", UserDto.class, id).getBody();
        return userDto;
    }

    @Override
    protected UserDto getFallback() {
        UserDto user = new UserDto();
        user.setNickname("error");
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
