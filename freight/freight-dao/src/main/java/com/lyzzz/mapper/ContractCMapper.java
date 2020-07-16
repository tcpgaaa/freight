package com.lyzzz.mapper;

import com.lyzzz.pojo.ContractC;
import com.lyzzz.pojo.ContractCVo;
import com.lyzzz.pojo.OutProduct;

import java.util.List;
import java.util.Map;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/9  14:09
@author:  Administrator
@remark:

*/
public interface ContractCMapper {
    int deleteByPrimaryKey(String contractId);

    int insert(ContractC record);

    int insertSelective(ContractC record);

    ContractC selectByPrimaryKey(String contractId);

    int updateByPrimaryKeySelective(ContractC record);

    int updateByPrimaryKey(ContractC record);
    List<ContractCVo> listContractOfPage(Map<String, Object> map);

    ContractCVo viewContractCByContractId(String contractId);

    List<ContractC> selectAll();

    List<OutProduct> listOutProductByMonth(String inputDate);

    List<ContractC> selectByList(List<String> contractIdList);

    int updateContract(ContractC contractC, Double cpMoney);



}