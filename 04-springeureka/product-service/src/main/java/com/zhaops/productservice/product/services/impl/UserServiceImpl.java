package com.zhaops.productservice.product.services.impl;

import com.zhaops.productservice.product.dto.UserDto;
import com.zhaops.productservice.product.redis.repository.UserRedisRepository;
import com.zhaops.productservice.product.services.UserRemoteClient;
import com.zhaops.productservice.product.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SoYuan
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRemoteClient userRemoteClient;

    @Autowired
    protected UserRedisRepository userRedisRepository;


    @Override
    public UserDto load(Long id) {
        UserDto userDto = this.userRedisRepository.findOne(id);

        if (userDto != null) {
            return userDto;
        }

        userDto = this.userRemoteClient.load(id);
        if (userDto != null) {
            this.userRedisRepository.saveUser(userDto);
        }

        return userDto;
    }
}
