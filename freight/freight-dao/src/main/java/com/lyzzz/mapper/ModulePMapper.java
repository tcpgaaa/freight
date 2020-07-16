package com.lyzzz.mapper;

import com.lyzzz.pojo.ModuleP;
import com.lyzzz.pojo.TreeNode;

import java.util.List;

/**
 * @location： freight\com.lyzzz.mapper
 * @creatTime: 2020/7/9  18:58
 * @author: Administrator
 * @remark:
 */
public interface ModulePMapper {
    int deleteByPrimaryKey(String moduleId);

    int insert(ModuleP record);

    int insertSelective(ModuleP record);

    ModuleP selectByPrimaryKey(String moduleId);

    int updateByPrimaryKeySelective(ModuleP record);

    int updateByPrimaryKey(ModuleP record);

    List<ModuleP> getPermissionsByUserId(String userId);

    List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid);

    List<ModuleP> selectByPage();

    List<ModuleP> selectAll();

    List<TreeNode> listModuleOfTreeBean();
}