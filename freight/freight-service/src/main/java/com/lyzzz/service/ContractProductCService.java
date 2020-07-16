package com.lyzzz.service;

import com.lyzzz.pojo.ContractProductC;
import com.lyzzz.pojo.PageBean;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/9  14:18
 * @author: Administrator
 * @remark:
 */
public interface ContractProductCService {


    int deleteByPrimaryKey(String contractProductId);

    int insert(ContractProductC record);

    int insertSelective(ContractProductC record);

    ContractProductC selectByPrimaryKey(String contractProductId);

    int updateByPrimaryKeySelective(ContractProductC record);

    int updateByPrimaryKey(ContractProductC record);

    PageBean listContractProductOfPage(PageBean pageBean, String contractId);

    void createContractProduct(ContractProductC contractProductC);

    int delete(String id, String cid);

}

