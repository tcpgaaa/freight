package com.lyzzz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/10  9:23
@author:  Administrator
@remark:

*/
@Data
public class ExtEproductC implements Serializable {
    private String extEproductId;

    private String factoryId;

    private String exportProductId;

    private String productNo;

    private String productImage;

    private String productDesc;

    private Integer cnumber;

    /**
    * PCS/SETS
    */
    private String packingUnit;

    private BigDecimal price;

    /**
    * 自动计算: 数量x单价
    */
    private BigDecimal amount;

    private String productRequest;

    private Integer orderNo;

    private static final long serialVersionUID = 1L;
}