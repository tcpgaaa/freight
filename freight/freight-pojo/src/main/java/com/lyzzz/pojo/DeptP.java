package com.lyzzz.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/6  20:08
 * @author: Administrator
 * @remark:
 */
@Data
public class DeptP implements Serializable {
    private String deptId;

    private String deptName;

    private String parentId;

    /**
     * 1代表启用，0代表停用，默认为1
     */
    private Integer state;

    private String deptNo;
}