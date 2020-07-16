package com.lyzzz.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/9  19:59
 * @author: Administrator
 * @remark:
 */
@Data
public class ContractProductC implements Serializable {
    private String contractProductId;

    private String contractId;

    private String factoryId;

    /**
     * 冗余
     */
    private String factoryName;

    private String productNo;

    private String productImage;

    private String productDesc;

    /**
     * 报运：x/y
     */
    private String loadingRate;

    /**
     * 报运
     */
    private Integer boxNum;

    /**
     * PCS/SETS
     */
    private String packingUnit;

    private Integer cnumber;

    /**
     * 报运
     */
    private Integer outNumber;

    /**
     * 报运
     */
    private Integer finished;

    private String productRequest;

    private BigDecimal price;

    /**
     * 自动计算: 数量x单价
     */
    private BigDecimal amount;

    private Integer orderNo;

    private static final long serialVersionUID = 1L;
}