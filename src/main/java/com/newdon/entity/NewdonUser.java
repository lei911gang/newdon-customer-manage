package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 用户信息表
 */
@Data
@TableName("newdon_user")
public class NewdonUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 帐号
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}

