package com.lyzzz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/13  11:27
@author:  Administrator
@remark:

*/
@Data
public class PackingListC implements Serializable {
    private String packingListId;

    private String seller;

    private String buyer;

    /**
    * 选择
    */
    private String invoiceNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    private String marks;

    private String descriptions;

    /**
    * 报运ID集合
    */
    private String exportIds;

    /**
    * 报运NO集合x,y|z,h
    */
    private String exportNos;

    /**
    * 0草稿 1已上报
    */
    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}