package com.lyzzz.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/9  20:00
@author:  Administrator
@remark:

*/
@Data
public class FactoryC implements Serializable {
    private String factoryId;

    /**
    * 货物/附件
    */
    private String ctype;

    private String fullName;

    private String factoryName;

    private String contacts;

    private String phone;

    private String mobile;

    private String fax;

    private String address;

    private String inspector;

    private String remark;

    private Integer orderNo;

    /**
    * 1正常0停用
    */
    private String state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}