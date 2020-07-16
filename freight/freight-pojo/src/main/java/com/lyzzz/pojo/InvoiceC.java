package com.lyzzz.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/13  11:35
@author:  Administrator
@remark:

*/
@Data
public class InvoiceC implements Serializable {
    /**
    * PACKING_LIST_ID值
    */
    private String invoiceId;

    /**
    * packing.getExportNos S/C就是报运的合同号
    */
    private String scNo;

    private String blNo;

    private String tradeTerms;

    /**
    * 0草稿 1已上报
    */
    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}