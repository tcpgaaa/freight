package com.lyzzz.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class OutProduct implements Serializable {

    private String customName;
    private String contractNo;
    private String deliveryPeriod;
    private String shipTime;
    private String tradeTerms;
    private String factoryName;
    private String productNo;
    private String cnumber;

}
