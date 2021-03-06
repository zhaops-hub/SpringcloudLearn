package com.zhaops.springbootshop.user.dto;

import com.google.common.base.MoreObjects;
import com.zhaops.springbootshop.user.entity.User;

import java.io.Serializable;

/**
 * @author SoYuan
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // ========================================================================
    // fields =================================================================
    private Long id;
    private String nickname;
    private String avatar;

    public UserDto() {

    }

    public UserDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
    }

    @Override
    public String toString() {
        return this.toStringHelper().toString();
    }

    protected MoreObjects.ToStringHelper toStringHelper() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("nickname", getNickname());
    }

    // ========================================================================
    // setter/getter ==========================================================
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
