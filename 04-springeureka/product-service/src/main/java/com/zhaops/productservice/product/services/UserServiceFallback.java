package com.zhaops.productservice.product.services;

import com.zhaops.productservice.product.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * @author SoYuan
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public UserDto load(Long id) {
        return new UserDto(id, "aaaaaaaaa", "default.png");
    }
}
