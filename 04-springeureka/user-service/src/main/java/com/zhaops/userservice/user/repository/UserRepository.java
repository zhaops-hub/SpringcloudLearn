package com.zhaops.userservice.user.repository;

import com.zhaops.userservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SoYuan
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
