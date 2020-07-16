package com.lyzzz.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/6  20:07
 * @author: Administrator
 * @remark:
 */
@Data
public class UserInfoP implements Serializable {

    private String userInfoId;

    private String name;

    private String email;

    private String managerId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;

    private BigDecimal salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String gender;

    private String station;

    private String telephone;

    /**
     * 0-超级管理员
     * 1-跨部门跨人员
     * 2-管理所有下属部门和人员
     * 3-管理本部门
     * 4-普通员工
     * <p>
     * <p>
     * 0作为内部控制只对sysdebug，用户不能进行添加
     */
    private Integer degree;

    private String remark;

    private Integer orderNo;

    /**
     * 登录人编号
     */
    private String createBy;

    /**
     * 登录人所属部门编号
     */
    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String updateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}