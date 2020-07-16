package com.lyzzz.service;

import com.lyzzz.pojo.ContractC;
import com.lyzzz.pojo.ContractCVo;
import com.lyzzz.pojo.OutProduct;
import com.lyzzz.pojo.PageBean;

import java.util.List;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/9  14:09
 * @author: Administrator
 * @remark:
 */
public interface ContractCService {


    int deleteByPrimaryKey(String contractId);

    int insert(ContractC record);

    int insertSelective(ContractC record);

    ContractC selectByPrimaryKey(String contractId);

    int updateByPrimaryKeySelective(ContractC record);

    int updateByPrimaryKey(ContractC record);

    PageBean listContractOfPage(PageBean pageBean, String f_type, String f_conditionStr, Integer state);

    void createContractC(ContractC contractC);

    void submitContractCByContractId(String contractId);

    ContractCVo viewContractCByContractId(String contractId);

    void cancelContractCByContractId(String contractId);

    List<ContractC> selectAll();


    List<OutProduct> listOutProductByMonth(String inputDate);
}
