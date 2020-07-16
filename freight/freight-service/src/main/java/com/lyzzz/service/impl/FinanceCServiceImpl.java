package com.lyzzz.service.impl;

import com.lyzzz.mapper.FinanceCMapper;
import com.lyzzz.pojo.CurrentUser;
import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.FinanceC;
import com.lyzzz.pojo.UserP;
import com.lyzzz.service.FinanceCService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/13  14:05
@author:  Administrator
@remark:

*/
@Service
public class FinanceCServiceImpl implements FinanceCService{

    @Resource
    private FinanceCMapper financeCMapper;

    @Override
    public int deleteByPrimaryKey(String financeId) {
        return financeCMapper.deleteByPrimaryKey(financeId);
    }

    @Override
    public int insert(FinanceC record) {
        return financeCMapper.insert(record);
    }

    @Override
    public int insertSelective(FinanceC record) {
        return financeCMapper.insertSelective(record);
    }

    @Override
    public FinanceC selectByPrimaryKey(String financeId) {
        return financeCMapper.selectByPrimaryKey(financeId);
    }

    @Override
    public int updateByPrimaryKeySelective(FinanceC record) {
        return financeCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FinanceC record) {
        return financeCMapper.updateByPrimaryKey(record);
    }

    @Override
    public void createFinanceC(FinanceC financeC) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //补全财务信息
        financeC.setState(0);
        financeC.setCreateBy(user.getUserId());
        financeC.setCreateDept(deptP.getDeptId());
        financeC.setCreateTime(new Date());
        financeCMapper.insertSelective(financeC);
    }

    @Override
    public void submitFinanceC(String packingListId) {
        FinanceC financeC = new FinanceC();
        financeC.setFinanceId(packingListId);
        financeC.setState(1);
        financeCMapper.updateByPrimaryKeySelective(financeC);
    }

    @Override
    public void cancelFinanceC(String packingListId) {
        FinanceC financeC = new FinanceC();
        financeC.setFinanceId(packingListId);
        financeC.setState(0);
        financeCMapper.updateByPrimaryKeySelective(financeC);
    }

}
