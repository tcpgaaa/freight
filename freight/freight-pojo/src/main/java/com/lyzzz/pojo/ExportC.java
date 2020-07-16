package com.lyzzz.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/10  9:17
@author:  Administrator
@remark:

*/
@Data
public class ExportC implements Serializable {
    private String exportId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    /**
    * ID集合串
            
            x,y,z
    */
    private String contractIds;

    /**
    * 客户的合同号,可选择多个合同
    */
    private String customerContract;

    /**
    * L/C T/T
    */
    private String lcno;

    private String consignee;

    private String marks;

    private String shipmentPort;

    private String destinationPort;

    /**
    * SEA/AIR
    */
    private String transportMode;

    /**
    * FBO/CIF
    */
    private String priceCondition;

    private String remark;

    /**
    * 冗余，为委托服务，一个报运的总箱数
    */
    private Integer boxNums;

    /**
    * 冗余，为委托服务，一个报运的总毛重
    */
    private BigDecimal grossWeights;

    /**
    * 冗余，为委托服务，一个报运的总体积
            
            =长x高x宽/100 00 00
            
            cm转换为m3 立方米

    */
    private BigDecimal measurements;

    /**
    * 0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
    */
    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}