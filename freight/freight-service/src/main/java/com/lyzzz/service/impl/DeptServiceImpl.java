package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.DeptPMapper;
import com.lyzzz.mapper.UserPMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptPMapper deptPMapper;

    @Autowired
    private UserPMapper userPMapper;

    @Override
    public PageBean listDeptOfPage(PageBean pb) {
        /**
         * 总共16条
         * p1  0,5
         * p2  5,5
         * p3  10,5
         *
         * 公式：(p-1)*5
         */
        Integer start = (pb.getCurPage()-1)*pb.getPageSize();

        PageHelper.offsetPage(start, pb.getPageSize());

        List<DeptPVo> deptPVoList  = deptPMapper.listDeptAndParent();

        /*deptPVoList.stream()
                .forEach(System.out::println);*/
        //把查询到的数据给pageinfo,PageInfo：获取分页信息
        PageInfo<DeptPVo> pageInfo = new PageInfo<>(deptPVoList);

        pb.setDatas(pageInfo.getList());
        pb.setTotalPages(pageInfo.getPages());
        pb.setTotalRows(pageInfo.getTotal());
        return pb;

    }

    @Override
    public List<DeptP> listDept() {
        List<DeptP> deptPS = deptPMapper.selectDeptList();
        return deptPS;
    }

    @Override
    public void addDept(DeptP deptP) {
        deptP.setDeptId(UUID.randomUUID().toString());
        deptP.setState(1);
        deptP.setDeptNo(String.valueOf(new Date().getTime()));
        deptPMapper.insertSelective(deptP);
    }

    @Override
    public DeptP getDeptById(String id) {
        return deptPMapper.selectByPrimaryKey(id);
    }

    @Override
    public DeptPVo DeptAndParentById(String id) {
        return deptPMapper.DeptAndParentById(id);
    }

    @Override
    public void updateDept(DeptP deptP) {
        deptPMapper.updateByPrimaryKey(deptP);
    }

    @Override
    public void delete(String id) {
        deptPMapper.deleteByPrimaryKey(id);
    }

    @Override
    // 删除
    public DeleteResult deleteDeptByDeptId(String[] deptIds) {

        List<String> deptName = new ArrayList<String>();
        for (int i = 0; i < deptIds.length; i++) {
            String deptId = deptIds[i];
            //判断该部门是否有子部门，如果有则不能删除
            if(isHaveChildDept(deptId)) {//shift+alt+m
                DeptP deptP = deptPMapper.selectByPrimaryKey(deptId);
                deptName.add(deptP.getDeptName());
                continue;
            }
            //有员工不能删除
            if(isHaveUser(deptId)){
                DeptP deptP = deptPMapper.selectByPrimaryKey(deptId);
                deptName.add(deptP.getDeptName());
                continue;
            }
            deptPMapper.deleteByPrimaryKey(deptId);
        }
        if(deptName.size()>0){
            return new DeleteResult(400,null,deptName);
        }
        return new DeleteResult(200,"删除成功！！！",null);
    }

    //判断该部门是否有员工
    private boolean isHaveUser(String deptId) {

        List<UserP> userPList = userPMapper.selectDeptHasUser(deptId);
        if(userPList!=null && userPList.size()>0){
            return true;
        }
        return false;
    }

    //判断该部门是否有子部门
    private boolean isHaveChildDept(String deptId) {

        List<DeptP> deptPList = deptPMapper.selectDeptHasChildDept(deptId);
        if(deptPList!=null && deptPList.size()>0){
            return true;
        }
        return false;
    }

}
