package com.zhaops.productservice.product.services;

import com.zhaops.productservice.product.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SoYuan
 */
@FeignClient(name = "USERSERVICE", fallback = UserServiceFallback.class)
public interface UserRemoteClient {

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    UserDto load(@PathVariable("id") Long id);
}
