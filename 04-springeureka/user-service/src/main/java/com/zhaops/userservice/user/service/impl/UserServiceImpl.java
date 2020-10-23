package com.zhaops.userservice.user.service.impl;

import com.zhaops.userservice.user.dto.UserDto;
import com.zhaops.userservice.user.entity.User;
import com.zhaops.userservice.user.repository.UserRepository;
import com.zhaops.userservice.user.service.UserMsg;
import com.zhaops.userservice.user.service.UserMsgSender;
import com.zhaops.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import brave.Tracer;

/**
 * @author SoYuan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserMsgSender userMsgSender;

    @Autowired
    protected Tracer tracer;

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
        this.userRepository.deleteById(id);

        // 发送用户删除消息
        this.sendMsg(UserMsg.UA_DELETE, id);
    }

    protected void sendMsg(String action, Long userId) {
        UserMsg msg = new UserMsg(action, userId, this.getTracerId());
        this.userMsgSender.sendMsg(msg);
    }

    protected String getTracerId() {
        return this.tracer.currentSpan().context().traceIdString();
    }
}
