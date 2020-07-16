package com.lyzzz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/13  14:34
@author:  Administrator
@remark:

*/
@Data
public class ShippingOrderC implements Serializable {
    /**
    * 等于PACKING_LIST_ID
    */
    private String shippingOrderId;

    /**
    * SEA海运AIR空运
    */
    private String orderType;

    private String shipper;

    private String consignee;

    private String notifyParty;

    private String lcNo;

    private String portOfLoading;

    private String portOfTrans;

    private String portOfDischarge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loadingDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    /**
    * 1是0否
    */
    private String isBatch;

    /**
    * 1是0否
    */
    private String isTrans;

    private String copyNum;

    private String remark;

    private String specialCondition;

    private String freight;

    private String checkBy;

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