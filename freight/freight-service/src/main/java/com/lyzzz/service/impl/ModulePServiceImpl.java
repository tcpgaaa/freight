package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ModulePMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.ModulePService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/9  18:58
@author:  Administrator
@remark:

*/
@Service
public class ModulePServiceImpl implements ModulePService{

    @Resource
    private ModulePMapper modulePMapper;

    @Override
    public int deleteByPrimaryKey(String moduleId) {
        return modulePMapper.deleteByPrimaryKey(moduleId);
    }

    @Override
    public int insert(ModuleP record) {
        // 获取当前用户登录信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP dept = currentUser.getDeptP();


        record.setModuleId(UUID.randomUUID().toString());
        record.setCreateTime(new Date());
        record.setCreateBy(user.getUserId());
        record.setCreateDept(dept.getDeptId());
        record.setUpdateBy(user.getUserId());
        record.setUpdateTime(new Date());
        return modulePMapper.insert(record);
    }

    @Override
    public int insertSelective(ModuleP record) {
        return modulePMapper.insertSelective(record);
    }

    @Override
    public ModuleP selectByPrimaryKey(String moduleId) {
        return modulePMapper.selectByPrimaryKey(moduleId);
    }

    @Override
    public int updateByPrimaryKeySelective(ModuleP record) {
        return modulePMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ModuleP record) {
        return modulePMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean listRoleOfPage(PageBean pageBean) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());

        List<ModuleP> modulePList = modulePMapper.selectByPage();
        modulePList.stream()
                .forEach(System.out::println);

        //获取分页信息
        PageInfo<ModuleP> pageInfo = new PageInfo<ModuleP>(modulePList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public List<ModuleP> selectAll() {
        return modulePMapper.selectAll();
    }

    @Override
    public List<TreeNode> listModuleOfTreeBean() {
        return modulePMapper.listModuleOfTreeBean();
    }

}
