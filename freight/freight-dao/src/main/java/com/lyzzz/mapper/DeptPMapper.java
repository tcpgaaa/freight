package com.lyzzz.mapper;

import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.DeptPVo;

import java.util.List;

/**
@location：  freight\com.lyzzz.mapper  
@creatTime:   2020/7/6  20:08
@author:  Administrator
@remark:

*/
public interface DeptPMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(DeptP record);

    int insertSelective(DeptP record);

    DeptP selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(DeptP record);

    int updateByPrimaryKey(DeptP record);

    List<DeptPVo> listDeptAndParent();
    DeptPVo DeptAndParentById(String deptId);

    List<DeptP> selectDeptList();

    List<DeptP> selectDeptHasChildDept(String deptId);
}