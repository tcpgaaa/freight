package com.lyzzz.mapper;

import com.lyzzz.pojo.ContractProductC;
import com.lyzzz.pojo.ContractProductCVo;
import com.lyzzz.pojo.ECharsData;

import java.util.List;

/**
 * @location： freight\com.lyzzz.mapper
 * @creatTime: 2020/7/9  19:59
 * @author: Administrator
 * @remark:
 */
public interface ContractProductCMapper {
    int deleteByPrimaryKey(String contractProductId);

    int insert(ContractProductC record);

    int insertSelective(ContractProductC record);

    ContractProductC selectByPrimaryKey(String contractProductId);

    int updateByPrimaryKeySelective(ContractProductC record);

    int updateByPrimaryKey(ContractProductC record);

    List<ContractProductCVo> listContractProductCOfPage(String contractId);

    Double getTotalAmountByContractId(String contractId);

    List<ContractProductCVo> listContractProductCByContractIds(String[] contractIds);
    // 厂家销售
    List<ECharsData> listFactorySaleData();
    // 产品销售
    List<ECharsData> listProductSaleData();
    // 系统访问
    List<ECharsData> listAccessLogData();

    Double getMoney(String id);

}