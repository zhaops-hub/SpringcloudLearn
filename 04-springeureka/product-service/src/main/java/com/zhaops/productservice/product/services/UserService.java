package com.zhaops.productservice.product.services;

import com.zhaops.productservice.product.dto.UserDto;

/**
 * @author SoYuan
 */
public interface UserService {
    /**
     * 加载用户id
     * @param id
     * @return
     */
    UserDto load(Long id);
}
