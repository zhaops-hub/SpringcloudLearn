package com.zhaops.springbootshop.user.repository;

import com.zhaops.springbootshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SoYuan
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
