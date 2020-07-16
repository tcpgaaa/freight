package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ContractCMapper;
import com.lyzzz.mapper.ContractProductCMapper;
import com.lyzzz.mapper.ExtCproductCMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.ExtCproductCService;
import com.lyzzz.utils.ArithUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/9  14:19
@author:  Administrator
@remark:

*/
@Service
public class ExtCproductCServiceImpl implements ExtCproductCService{

    @Resource
    private ExtCproductCMapper extCproductCMapper;
    @Autowired
    private ContractProductCMapper contractProductCMapper;
    @Autowired
    private ContractCMapper contractCMapper;

    @Override
    public int deleteByPrimaryKey(String extCproductId) {
        return extCproductCMapper.deleteByPrimaryKey(extCproductId);
    }

    @Override
    public int insert(ExtCproductC record) {
        return extCproductCMapper.insert(record);
    }

    @Override
    public int insertSelective(ExtCproductC record) {
        return extCproductCMapper.insertSelective(record);
    }

    @Override
    public ExtCproductC selectByPrimaryKey(String extCproductId) {
        return extCproductCMapper.selectByPrimaryKey(extCproductId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExtCproductC record) {
        return extCproductCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExtCproductC record) {
        return extCproductCMapper.updateByPrimaryKey(record);
    }

    @Override
    public void createExtCproductC(ExtCproductC extCproductC, String cid) {
        extCproductC.setContractProductId(UUID.randomUUID().toString());
        extCproductC.setExtCproductId(UUID.randomUUID().toString());
        //1、计算附件的总金额
        Double amount = ArithUtil.mul(extCproductC.getCnumber().doubleValue(), extCproductC.getPrice().doubleValue());
        extCproductC.setAmount(new BigDecimal(amount.toString()));
        //2、保存附件信息
        extCproductCMapper.insertSelective(extCproductC);

        //3、修改合同的总金额
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        //a、查询货物的总金额金额
        Double totalAmount_contractProduct = contractProductCMapper.getTotalAmountByContractId(cid);
        //b、查询附件的总金额金额
        Double totalAmount_extCproduct = extCproductCMapper.getTotalAmountByContractId(cid);

        if(totalAmount_extCproduct==null){
            totalAmount_extCproduct = 0.0;
        }

        //c、货物的总金额+附件的总金额
        Double totalAmount = ArithUtil.add(totalAmount_contractProduct, totalAmount_extCproduct);

        ContractC contractC = new ContractC();
        contractC.setContractId(cid);
        contractC.setTotalAmount(new BigDecimal(totalAmount.toString()));
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());
        contractCMapper.updateByPrimaryKeySelective(contractC);
    }

    @Override
    public PageBean selectAll(PageBean pageBean, String cid) {
        PageHelper.startPage(pageBean.getCurPage(), pageBean.getPageSize());
        List<ExtCproductC> extCproductCList = extCproductCMapper.getPageList(cid);
        //获取分页信息
        PageInfo<ExtCproductC> pageInfo = new PageInfo<>(extCproductCList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

}
