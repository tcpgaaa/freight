package com.lyzzz.service.impl;

import com.lyzzz.mapper.InvoiceCMapper;
import com.lyzzz.pojo.CurrentUser;
import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.InvoiceC;
import com.lyzzz.pojo.UserP;
import com.lyzzz.service.InvoiceCService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/13  11:35
@author:  Administrator
@remark:

*/
@Service
public class InvoiceCServiceImpl implements InvoiceCService{

    @Resource
    private InvoiceCMapper invoiceCMapper;

    @Override
    public int deleteByPrimaryKey(String invoiceId) {
        return invoiceCMapper.deleteByPrimaryKey(invoiceId);
    }

    @Override
    public int insert(InvoiceC record) {
        return invoiceCMapper.insert(record);
    }

    @Override
    public int insertSelective(InvoiceC record) {
        return invoiceCMapper.insertSelective(record);
    }

    @Override
    public InvoiceC selectByPrimaryKey(String invoiceId) {
        return invoiceCMapper.selectByPrimaryKey(invoiceId);
    }

    @Override
    public int updateByPrimaryKeySelective(InvoiceC record) {
        return invoiceCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InvoiceC record) {
        return invoiceCMapper.updateByPrimaryKey(record);
    }

    @Override
    public void createInvoice(InvoiceC invoiceC) {
//获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //补全发票信息
        String[] invoiceId_scNo = invoiceC.getInvoiceId().split("#");
        String invoiceId = invoiceId_scNo[0];
        String scNo = invoiceId_scNo[1];

        invoiceC.setInvoiceId(invoiceId);
        invoiceC.setScNo(scNo);
        invoiceC.setState(0);
        invoiceC.setCreateBy(user.getUserId());
        invoiceC.setCreateDept(deptP.getDeptId());
        invoiceC.setCreateTime(new Date());
        invoiceCMapper.insertSelective(invoiceC);
    }

    @Override
    public void submitInvoice(String packingListId) {
        InvoiceC invoiceC = new InvoiceC();
        invoiceC.setState(1);
        invoiceC.setInvoiceId(packingListId.split("#")[0]);
        invoiceCMapper.updateByPrimaryKeySelective(invoiceC);

    }

    @Override
    public void cancelFinanceC(String packingListId) {
        InvoiceC invoiceC = new InvoiceC();
        invoiceC.setState(0);
        invoiceC.setInvoiceId(packingListId.split("#")[0]);
        invoiceCMapper.updateByPrimaryKeySelective(invoiceC);
    }
}
