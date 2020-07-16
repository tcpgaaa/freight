package com.lyzzz.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class PackingListCVo extends PackingListC{
    //委托状态
    private Integer shippingOrderState;
    //发票状态
    private Integer invoiceState;
    //财务状态
    private Integer financeState;

}