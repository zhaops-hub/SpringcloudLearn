package com.zhaops.springbootshop.user.service.impl;

import com.zhaops.springbootshop.user.dto.UserDto;
import com.zhaops.springbootshop.user.entity.User;
import com.zhaops.springbootshop.user.repository.UserRepository;
import com.zhaops.springbootshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author SoYuan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;


    @Override
    public Page<User> getPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User load(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public User save(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
