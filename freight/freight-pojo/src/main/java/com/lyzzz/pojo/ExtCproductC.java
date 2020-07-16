package com.lyzzz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/9  14:19
@author:  Administrator
@remark:

*/
@Data
public class ExtCproductC implements Serializable {
    private String extCproductId;

    private String contractProductId;

    private String factoryId;

    private String factoryName;

    private String productNo;

    private String productImage;

    private String productDesc;

    /**
    * PCS/SETS
    */
    private String packingUnit;

    private Integer cnumber;

    private BigDecimal price;

    /**
    * 自动计算: 数量x单价
    */
    private BigDecimal amount;

    private String productRequest;

    private Integer orderNo;

    private static final long serialVersionUID = 1L;
}