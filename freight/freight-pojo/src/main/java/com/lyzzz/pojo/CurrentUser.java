package com.lyzzz.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CurrentUser implements Serializable {

    //用户信息
    private UserP userP;

    //用户扩展信息
    private UserInfoP userInfoP;

    //部门信息
    private DeptP deptP;

}
