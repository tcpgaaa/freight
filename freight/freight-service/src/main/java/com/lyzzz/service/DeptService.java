package com.lyzzz.service;


import com.lyzzz.pojo.DeleteResult;
import com.lyzzz.pojo.DeptP;
import com.lyzzz.pojo.DeptPVo;
import com.lyzzz.pojo.PageBean;

import java.util.List;

public interface DeptService {
    PageBean listDeptOfPage(PageBean pb);

    List<DeptP> listDept();

    void addDept(DeptP deptP);

    DeptP getDeptById(String id);
    DeptPVo DeptAndParentById(String id);

    void updateDept(DeptP deptP);

    void delete(String id);
    DeleteResult deleteDeptByDeptId(String[] deptIds);

}
