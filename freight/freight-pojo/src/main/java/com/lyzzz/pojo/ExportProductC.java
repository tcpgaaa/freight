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
public class ExportProductC implements Serializable {
    private String exportProductId;

    private String exportId;

    private String factoryId;

    private String productNo;

    /**
    * PCS/SETS
    */
    private String packingUnit;

    private Integer cnumber;

    private Integer boxNum;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private BigDecimal sizeLength;

    private BigDecimal sizeWidth;

    private BigDecimal sizeHeight;

    /**
    * sales confirmation 中的价格（手填）
    */
    private BigDecimal exPrice;

    private BigDecimal price;

    /**
    * 收购单价=合同单价
    */
    private BigDecimal tax;

    private Integer orderNo;

    private static final long serialVersionUID = 1L;
}