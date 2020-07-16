package com.lyzzz.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
@location：  freight\com.lyzzz.pojo  
@creatTime:   2020/7/9  14:09
@author:  Administrator
@remark:

*/
@Data
@ToString
public class ContractC implements Serializable {
    private String contractId;

    private String offeror;

    private String contractNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signingDate;

    private String inputBy;

    private String checkBy;

    private String inspector;

    private BigDecimal totalAmount;

    private String crequest;

    private String customName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date shipTime;

    /**
    * 打印时标识几个星,对应说明中的内容
            不能存储特殊字符星星，jstl判断失效
    */
    private Integer importNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryPeriod;

    /**
    * 归档前状态, 方便回退
    */
    private Integer oldState;

    /**
    * 0未走货 1部分 2全部
            
            归档后, 其他选择合同的地方均去除.
            表示合同已完成, 不论是否合同的货物是否全部真的走完, 因为有赔付等其他情况
    */
    private Integer outState;

    private String tradeTerms;

    /**
    * 宽2:一页两个货物  窄1:一页一个货物
    */
    private String printStyle;

    private String remark;

    /**
    * 0草稿 1已上报待报运
            
            归档后, 其他选择合同的地方均去除.
            表示合同已完成, 不论是否合同的货物是否全部真的走完, 因为有赔付等其他情况
    */
    private Integer state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}