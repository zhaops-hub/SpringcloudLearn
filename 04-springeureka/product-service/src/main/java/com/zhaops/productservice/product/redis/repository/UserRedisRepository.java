package com.zhaops.productservice.product.redis.repository;

import com.zhaops.productservice.product.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @author SoYuan
 */
@Repository
public class UserRedisRepository {
    public static final String USER_KEY_PRE = "user:";

    @Autowired
    private RedisTemplate<String, Serializable> userRedisTemplate;
    private ValueOperations<String, Serializable> operations;

    @PostConstruct
    private void init() {
        this.operations = this.userRedisTemplate.opsForValue();
    }

    /**
     * 保存
     * @param userDto
     * @return
     */
    public void saveUser(UserDto userDto) {
        this.operations.set(this.buildKey(userDto.getId()), userDto);
    }

    /**
     * 加载
     * @param userId
     * @return
     */
    public UserDto findOne(Long userId) {
        String key = this.buildKey(userId);
        if (!this.userRedisTemplate.hasKey(key))
            return null;

        return (UserDto) this.operations.get(key);
    }

    /**
     * 删除
     * @param userId
     */
    public void delete(Long userId) {
        this.userRedisTemplate.delete(this.buildKey(userId));
    }

    /**
     * 构造存储的Key
     * @param userId
     * @return
     */
    protected String buildKey(Long userId) {
        return USER_KEY_PRE + String.valueOf(userId);
    }
}
