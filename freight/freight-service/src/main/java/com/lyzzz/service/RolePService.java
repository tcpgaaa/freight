package com.lyzzz.service;

import com.lyzzz.pojo.DeleteResult;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.pojo.RoleP;

import com.lyzzz.pojo.TreeNode;
import java.util.List;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/8  8:51
 * @author: Administrator
 * @remark:
 */
public interface RolePService {


    int deleteByPrimaryKey(String roleId);

    int insert(RoleP record);

    int insertSelective(RoleP record);

    RoleP selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(RoleP record);

    int updateByPrimaryKey(RoleP record);

    List<RoleP> selectAll();

    PageBean listRoleOfPage(PageBean pageBean);

    DeleteResult deleteRole(String[] roleIds);

    List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid);
}
