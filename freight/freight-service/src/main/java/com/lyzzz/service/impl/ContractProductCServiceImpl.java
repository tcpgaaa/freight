package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ContractCMapper;
import com.lyzzz.mapper.ContractProductCMapper;
import com.lyzzz.mapper.ExtCproductCMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.ContractProductCService;
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
 * @location： freight\com.lyzzz.service.impl
 * @creatTime: 2020/7/9  14:18
 * @author: Administrator
 * @remark:
 */
@Service
public class ContractProductCServiceImpl implements ContractProductCService {

    @Resource
    private ContractProductCMapper contractProductCMapper;
    @Autowired
    private ContractCMapper contractCMapper;

    @Autowired
    private ExtCproductCMapper extCproductCMapper;

    @Override
    public int deleteByPrimaryKey(String contractProductId) {
        return contractProductCMapper.deleteByPrimaryKey(contractProductId);
    }

    @Override
    public int insert(ContractProductC record) {
        return contractProductCMapper.insert(record);
    }

    @Override
    public int insertSelective(ContractProductC record) {
        return contractProductCMapper.insertSelective(record);
    }

    @Override
    public ContractProductC selectByPrimaryKey(String contractProductId) {
        return contractProductCMapper.selectByPrimaryKey(contractProductId);
    }


    @Override
    public int updateByPrimaryKey(ContractProductC record) {
        return contractProductCMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean listContractProductOfPage(PageBean pageBean, String contractId) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(), pageBean.getPageSize());
        List<ContractProductCVo> contractProductCVoList = contractProductCMapper.listContractProductCOfPage(contractId);

        //获取分页信息
        PageInfo<ContractProductCVo> pageInfo = new PageInfo<>(contractProductCVoList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public void createContractProduct(ContractProductC contractProductC) {
        contractProductC.setContractProductId(UUID.randomUUID().toString());
        //1、计算货物的总金额
        Double amount = ArithUtil.mul(contractProductC.getCnumber().doubleValue(), contractProductC.getPrice().doubleValue());
        contractProductC.setAmount(new BigDecimal(amount.toString()));
        //2、保存货物信息
        contractProductCMapper.insertSelective(contractProductC);

        //3、修改合同的总金额
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        //a、查询货物的总金额金额
        Double totalAmount_contractProduct = contractProductCMapper.getTotalAmountByContractId(contractProductC.getContractId());
        //b、查询附件的总金额金额
        Double totalAmount_extCproduct = extCproductCMapper.getTotalAmountByContractId(contractProductC.getContractId());

        if(totalAmount_extCproduct==null){
            totalAmount_extCproduct = 0.0;
        }

        //c、货物的总金额+附件的总金额
        Double totalAmount = ArithUtil.add(totalAmount_contractProduct, totalAmount_extCproduct);

        ContractC contractC = new ContractC();
        contractC.setContractId(contractProductC.getContractId());
        contractC.setTotalAmount(new BigDecimal(totalAmount.toString()));
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());
        contractCMapper.updateByPrimaryKeySelective(contractC);
    }

    @Override
    public int delete(String id, String cid) {
        // 修改合同金额删除当前货物并级联删除货物下的附件
        //修改合同的总金额
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        // 查询合同的总金额
        ContractC c = contractCMapper.selectByPrimaryKey(cid);

        //查询货物的总金额
        Double cpMoney = contractProductCMapper.getMoney(id);

        ContractC contractC = new ContractC();
        contractC.setContractId(cid);
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());

        int d3 = contractCMapper.updateContract(contractC,cpMoney);

        // 删除货物
        int delete = contractProductCMapper.deleteByPrimaryKey(id);
        // 删除附件
        int d2 = extCproductCMapper.delete(id);

         return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ContractProductC contractProductC) {
        Double amount = ArithUtil.mul(contractProductC.getCnumber().doubleValue(), contractProductC.getPrice().doubleValue());
        contractProductC.setAmount(new BigDecimal(amount.toString()));
        contractProductCMapper.updateByPrimaryKeySelective(contractProductC);

        //修改合同的总金额
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        //a、查询货物的总金额金额
        Double totalAmount_contractProduct = contractProductCMapper.getTotalAmountByContractId(contractProductC.getContractId());
        //b、查询附件的总金额金额
        Double totalAmount_extCproduct = extCproductCMapper.getTotalAmountByContractId(contractProductC.getContractId());

        if(totalAmount_extCproduct==null){
            totalAmount_extCproduct = 0.0;
        }

        //c、货物的总金额+附件的总金额
        Double totalAmount = ArithUtil.add(totalAmount_contractProduct, totalAmount_extCproduct);

        ContractC contractC = new ContractC();
        contractC.setContractId(contractProductC.getContractId());
        contractC.setTotalAmount(new BigDecimal(totalAmount.toString()));
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());
        contractCMapper.updateByPrimaryKeySelective(contractC);

        return 1;
    }


}

