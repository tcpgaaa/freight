package com.lyzzz.service.impl;

import com.lyzzz.mapper.ShippingOrderCMapper;
import com.lyzzz.pojo.CurrentUser;
import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.ShippingOrderC;
import com.lyzzz.pojo.UserP;
import com.lyzzz.service.ShippingOrderCService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @location： freight\com.lyzzz.service.impl
 * @creatTime: 2020/7/13  14:33
 * @author: Administrator
 * @remark:
 */
@Service
public class ShippingOrderCServiceImpl implements ShippingOrderCService {

    @Resource
    private ShippingOrderCMapper shippingOrderCMapper;

    @Override
    public int deleteByPrimaryKey(String shippingOrderId) {
        return shippingOrderCMapper.deleteByPrimaryKey(shippingOrderId);
    }

    @Override
    public int insert(ShippingOrderC record) {
        return shippingOrderCMapper.insert(record);
    }

    @Override
    public int insertSelective(ShippingOrderC record) {
        return shippingOrderCMapper.insertSelective(record);
    }

    @Override
    public ShippingOrderC selectByPrimaryKey(String shippingOrderId) {
        return shippingOrderCMapper.selectByPrimaryKey(shippingOrderId);
    }

    @Override
    public int updateByPrimaryKeySelective(ShippingOrderC record) {
        return shippingOrderCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShippingOrderC record) {
        return shippingOrderCMapper.updateByPrimaryKey(record);
    }

    @Override
    public void submitShippingOrder(String packingListId) {
        ShippingOrderC shippingOrderC = new ShippingOrderC();
        shippingOrderC.setShippingOrderId(packingListId);
        shippingOrderC.setState(1);
        shippingOrderCMapper.updateByPrimaryKeySelective(shippingOrderC);
    }
    @Override
    public void cancelShippingOrder(String packingListId) {
        ShippingOrderC shippingOrderC = new ShippingOrderC();
        shippingOrderC.setShippingOrderId(packingListId);
        shippingOrderC.setState(0);
        shippingOrderCMapper.updateByPrimaryKeySelective(shippingOrderC);
    }

    @Override
    public void createShippingOrder(ShippingOrderC shippingOrderC) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //补全委托单信息
        shippingOrderC.setState(0);
        shippingOrderC.setCreateBy(user.getUserId());
        shippingOrderC.setCreateDept(deptP.getDeptId());
        shippingOrderC.setCreateTime(new Date());
        shippingOrderCMapper.insertSelective(shippingOrderC);
    }

}

