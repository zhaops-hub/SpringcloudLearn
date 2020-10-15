package com.zhaops.springbootshop.user.service;

import com.zhaops.springbootshop.user.dto.UserDto;
import com.zhaops.springbootshop.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author SoYuan
 */
public interface UserService {
    /**
     * 获取商品配置的分页数据
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<User> getPage(Pageable pageable);

    /**
     * 加载指定的用户信息
     * @param id 用户主键
     * @return
     */
    User load(Long id);

    /**
     * 保存/更新用户
     * @param userDto
     * @return
     */
    User save(UserDto userDto);

    /**
     * 删除指定的用户
     * @param id 所要删除的用户主键
     */
    void delete(Long id);
}
