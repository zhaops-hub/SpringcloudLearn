package com.zhaops.userservice.user.api;

import com.zhaops.userservice.user.dto.UserDto;
import com.zhaops.userservice.user.entity.User;
import com.zhaops.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SoYuan
 */
@RestController
@RequestMapping("/users")
public class UserEndpoint {

    @Value("${server.port}")
    protected int servicePort = 0;

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> findAll(Pageable pageable) {
        Page<User> page = this.userService.getPage(pageable);
        if (null != page) {
            return page.getContent().stream().map((user) -> {
                return new UserDto(user, servicePort);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto detail(@PathVariable Long id) {
        User user = this.userService.load(id);
        return (null != user) ? new UserDto(user, servicePort) : null;
    }
}
