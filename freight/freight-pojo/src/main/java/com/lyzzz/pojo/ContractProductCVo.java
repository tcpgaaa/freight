package com.lyzzz.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ContractProductCVo extends ContractProductC{
    //附件
    private List<ExtCproductC> extCproductCList;

}