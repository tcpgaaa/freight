package com.lyzzz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/8  8:50
 * @author: Administrator
 * @remark:
 */
@Data
public class RoleUserP implements Serializable {
    private String roleId;

    private String userId;

    private static final long serialVersionUID = 1L;

    public RoleUserP(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public RoleUserP() {
    }
}