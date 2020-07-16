package com.lyzzz.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/9  14:10
 * @author: Administrator
 * @remark:
 */
@Data
@ToString(callSuper = true)
public class ContractCVo extends ContractC implements Serializable {
    private Integer contractProductNum;
    private Integer extCproductNum;
    //货物
    private List<ContractProductCVo> extCproductCList;

}
