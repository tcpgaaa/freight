package com.lyzzz.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/8  8:51
 * @author: Administrator
 * @remark:
 */
@Data
public class RoleP implements Serializable {
    private String roleId;

    private String name;

    private String remark;

    private Integer orderNo;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}