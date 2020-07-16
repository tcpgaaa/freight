package com.lyzzz.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/6  17:52
 * @author: Administrator
 * @remark:
 */
@Data
@ContentRowHeight(20)
@ColumnWidth(20)
public class UserP implements Serializable {
    @ExcelProperty("用户编号")
    private String userId;
    @ExcelProperty("部门编号")
    private String deptId;

    /**
     * 不能重复,可为中文
     */
    @ExcelProperty("用户名")
    private String userName;

    /**
     * shiro MD5密码32位
     */
    @ExcelProperty("密码")
    private String password;

    /**
     * 1启用0停用
     */
    @ExcelProperty("状态")
    private Integer state;

    /**
     * 登录人编号
     */
    @ExcelProperty("创建人编号")
    private String createBy;

    /**
     * 登录人所属部门编号
     */
    @ExcelProperty("创建人部门")
    private String createDept;
    @ExcelProperty("添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @ExcelProperty("修改人编号")
    private String updateBy;
    @ExcelProperty("修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}