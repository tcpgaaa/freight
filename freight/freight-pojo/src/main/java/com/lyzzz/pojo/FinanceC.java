package com.lyzzz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/13  14:05
@author:  Administrator
@remark:

*/
@Data
public class FinanceC implements Serializable {
    private String financeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    private String inputBy;

    /**
    * 0草稿 1已上报
    */
    private Integer state;

    private String createBy;

    private String createDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}