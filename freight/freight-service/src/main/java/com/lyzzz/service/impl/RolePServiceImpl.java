package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ModulePMapper;
import com.lyzzz.mapper.RoleModulePMapper;
import com.lyzzz.mapper.RolePMapper;
import com.lyzzz.mapper.RoleUserPMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.RolePService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/8  8:51
@author:  Administrator
@remark:

*/
@Service("rolePService")
public class RolePServiceImpl implements RolePService{

    @Resource
    private RolePMapper rolePMapper;
    @Autowired
    private RoleUserPMapper roleUserPMapper;
    @Autowired
    private ModulePMapper modulePMapper;
    @Autowired
    private RoleModulePMapper roleModulePMapper;

    @Override
    public int deleteByPrimaryKey(String roleId) {
        return rolePMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(RoleP record) {
        return rolePMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleP roleP) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //补全角色的信息
        roleP.setRoleId(UUID.randomUUID().toString());
        roleP.setCreateBy(user.getUserId());
        roleP.setCreateDept(deptP.getDeptId());
        roleP.setUpdateBy(user.getUserId());
        roleP.setCreateTime(new Date());
        roleP.setUpdateTime(new Date());

        //获取最大的orderNo
        Integer orderNo = rolePMapper.getMaxOrderNo();
        roleP.setOrderNo(orderNo);
        return rolePMapper.insertSelective(roleP);
    }

    @Override
    public RoleP selectByPrimaryKey(String roleId) {
        return rolePMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleP record) {
        return rolePMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleP record) {
        return rolePMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RoleP> selectAll() {
        return rolePMapper.selectAll();
    }

    @Override
    public PageBean listRoleOfPage(PageBean pageBean) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());

        List<RoleP> rolePList = rolePMapper.selectByOrder();
        //获取分页信息
        PageInfo<RoleP> pageInfo = new PageInfo<RoleP>(rolePList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public DeleteResult deleteRole(String[] roleIds) {

        List<String> roleList = new ArrayList<String>();

        for (int i = 0; i < roleIds.length; i++) {
            String roleId = roleIds[i];
            // 有没有用户是此角色
            if(isNot(roleId)) {
                RoleP roleP = rolePMapper.selectByPrimaryKey(roleId);
                roleList.add(roleP.getName());
                continue;
            }
            roleModulePMapper.deleteById(roleId);
            rolePMapper.deleteByPrimaryKey(roleId);
        }
        if(roleList.size()>0){
            return new DeleteResult(400,null,roleList);
        }
        return new DeleteResult(200,"删除成功！！！",null);
    }

    @Override
    public List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid) {
        //1、查询当前角色所拥有的权限： select id,pid,name,'true' checked from module_p
        //中间用union all连接
        //2、查询当前角色所不拥有的权限：select id,pid,name,'false' checked from module_p
        return modulePMapper.listModuleOfTreeBeanByRoleId(roleid);
    }

    //有没有用户是此角色
    private boolean isNot(String roleId) {
        List<RoleUserP> roleUserPList = roleUserPMapper.selectById(roleId);

        if(roleUserPList!=null && roleUserPList.size()>0){
            return true;
        }
        return false;
    }

}
