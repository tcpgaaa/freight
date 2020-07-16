package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ContractCMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.ContractCService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/9  14:09
@author:  Administrator
@remark:

*/
@Service
public class ContractCServiceImpl implements ContractCService{

    @Resource
    private ContractCMapper contractCMapper;

    @Override
    public int deleteByPrimaryKey(String contractId) {
        return contractCMapper.deleteByPrimaryKey(contractId);
    }

    @Override
    public int insert(ContractC record) {
        return contractCMapper.insert(record);
    }

    @Override
    public int insertSelective(ContractC record) {
        return contractCMapper.insertSelective(record);
    }

    @Override
    public ContractC selectByPrimaryKey(String contractId) {
        return contractCMapper.selectByPrimaryKey(contractId);
    }

    @Override
    public int updateByPrimaryKeySelective(ContractC record) {
        return contractCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ContractC record) {
        return contractCMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean listContractOfPage(PageBean pageBean, String f_type, String f_conditionStr, Integer state) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        Map<String, Object> map = new HashMap<String, Object>();
        //是否按状态查询
        if(state!=null){
            map.put("state",state);
        }
        if(StringUtils.isNoneBlank(f_type) && StringUtils.isNoneBlank(f_conditionStr)){
            if("hth".equals(f_type)){
                map.put("hth",f_conditionStr);
            }else if("hh".equals(f_type)){
                map.put("hh",f_conditionStr);
            }else if("zdr".equals(f_type)){
                map.put("zdr",f_conditionStr);
            }else if("sdr".equals(f_type)){
                map.put("sdr",f_conditionStr);
            }else if("yhy".equals(f_type)){
                map.put("yhy",f_conditionStr);
            }
        }
        List<ContractCVo> contractCList = contractCMapper.listContractOfPage(map);
        System.out.println("----------------------------------");
        contractCList.stream()
                .forEach(System.out::println);
        System.out.println("----------------------------------");

        //获取分页信息
        PageInfo<ContractCVo> pageInfo = new PageInfo<ContractCVo>(contractCList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public void createContractC(ContractC contractC) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //补全角色的信息
        contractC.setContractId(UUID.randomUUID().toString());
        contractC.setCreateBy(user.getUserId());
        contractC.setCreateDept(deptP.getDeptId());
        contractC.setUpdateBy(user.getUserId());
        contractC.setCreateTime(new Date());
        contractC.setUpdateTime(new Date());
        contractC.setState(0);
        contractCMapper.insertSelective(contractC);

    }

    @Override
    public void submitContractCByContractId(String contractId) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();

        ContractC contractC = new ContractC();
        contractC.setContractId(contractId);
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());
        contractC.setState(1);
        contractCMapper.updateByPrimaryKeySelective(contractC);

    }

    @Override
    public ContractCVo viewContractCByContractId(String contractId) {
        return contractCMapper.viewContractCByContractId(contractId);
    }

    @Override
    public void cancelContractCByContractId(String contractId) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();

        ContractC contractC = new ContractC();
        contractC.setContractId(contractId);
        contractC.setUpdateBy(user.getUserId());
        contractC.setUpdateTime(new Date());
        contractC.setState(0);
        contractCMapper.updateByPrimaryKeySelective(contractC);
    }

    @Override
    public List<ContractC> selectAll() {
        return contractCMapper.selectAll();
    }

    @Override
    public List<OutProduct> listOutProductByMonth(String inputDate) {
        return contractCMapper.listOutProductByMonth(inputDate);
    }


}
