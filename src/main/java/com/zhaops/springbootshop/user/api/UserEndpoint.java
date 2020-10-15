package com.zhaops.springbootshop.user.api;

import com.zhaops.springbootshop.user.dto.UserDto;
import com.zhaops.springbootshop.user.entity.User;
import com.zhaops.springbootshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserEndpoint.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> findAll(Pageable pageable) {
        log.debug("开始查询");
        Page<User> page = this.userService.getPage(pageable);
        if (null != page) {
            return page.getContent().stream().map((user) -> {
                return new UserDto(user);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto detail(@PathVariable Long id) {
        User user = this.userService.load(id);
        return (null != user) ? new UserDto(user) : null;
    }
}
