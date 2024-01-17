package com.taffy.neko.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    //用户id
    Long userId;

    //角色id
    Long roleId;
}
