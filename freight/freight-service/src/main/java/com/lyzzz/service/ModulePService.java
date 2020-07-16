package com.lyzzz.service;

import com.lyzzz.pojo.ModuleP;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.pojo.TreeNode;

import java.util.List;

/**
 * @location： freight\com.lyzzz.service
 * @creatTime: 2020/7/9  18:58
 * @author: Administrator
 * @remark:
 */
public interface ModulePService {


    int deleteByPrimaryKey(String moduleId);

    int insert(ModuleP record);

    int insertSelective(ModuleP record);

    ModuleP selectByPrimaryKey(String moduleId);

    int updateByPrimaryKeySelective(ModuleP record);

    int updateByPrimaryKey(ModuleP record);

    PageBean listRoleOfPage(PageBean pageBean);

    List<ModuleP> selectAll();

    List<TreeNode> listModuleOfTreeBean();

}
