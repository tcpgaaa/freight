package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.ExportCMapper;
import com.lyzzz.mapper.PackingListCMapper;
import com.lyzzz.pojo.*;
import com.lyzzz.service.PackingListCService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/13  11:27
@author:  Administrator
@remark:

*/
@Service
public class PackingListCServiceImpl implements PackingListCService{

    @Resource
    private PackingListCMapper packingListCMapper;

    @Autowired
    private ExportCMapper exportCMapper;

    @Override
    public int deleteByPrimaryKey(String packingListId) {
        return packingListCMapper.deleteByPrimaryKey(packingListId);
    }

    @Override
    public int insert(PackingListC record) {
        return packingListCMapper.insert(record);
    }

    @Override
    public int insertSelective(PackingListC record) {
        return packingListCMapper.insertSelective(record);
    }

    @Override
    public PackingListC selectByPrimaryKey(String packingListId) {
        return packingListCMapper.selectByPrimaryKey(packingListId);
    }

    @Override
    public int updateByPrimaryKeySelective(PackingListC record) {
        return packingListCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PackingListC record) {
        return packingListCMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean listPackingListOfPageByState(PageBean pageBean) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        List<PackingListCVo> packingListCVoList = packingListCMapper.listPackingListVoOfPageByState();

        //获取分页信息
        PageInfo<PackingListCVo> pageInfo = new PageInfo<>(packingListCVoList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public PageBean listPackingListOfPage(PageBean pageBean) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        List<PackingListC> packingListCList = packingListCMapper.selectAll();

        //获取分页信息
        PageInfo<PackingListC> pageInfo = new PageInfo<PackingListC>(packingListCList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public int createPackingList(String[] exportId, PackingListC packingListC) {
        //1、获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //2、处理EXPORT_IDS和EXPORT_NOS
        StringBuffer exportIds = new StringBuffer();
        StringBuffer exportNos = new StringBuffer();
        for (int i = 0; i < exportId.length; i++) {
            String str = exportId[i];
            String[] exportId_exportNo = str.split("#");
            if(StringUtils.isNotBlank(exportIds)){
                exportIds.append(",");
            }
            if(StringUtils.isNotBlank(exportNos)){
                exportNos.append(",");
            }
            exportIds.append(exportId_exportNo[0]);
            exportNos.append(exportId_exportNo[1]);
        }

        //3、补全装箱单信息
        packingListC.setExportIds(exportIds.toString());
        packingListC.setExportNos(exportNos.toString());
        packingListC.setPackingListId(UUID.randomUUID().toString());
        packingListC.setCreateBy(user.getUserId());
        packingListC.setCreateDept(deptP.getDeptId());
        packingListC.setCreateTime(new Date());
        packingListC.setState(0);
        packingListCMapper.insertSelective(packingListC);

        //4、修改报运单的状态
        String[] exportId_arr = exportIds.toString().split(",");
        for (int i = 0; i < exportId_arr.length; i++) {
            String exportId1 = exportId_arr[i];
            ExportC exportC = new ExportC();
            exportC.setExportId(exportId1);
            exportC.setState(2);
            exportCMapper.updateByPrimaryKeySelective(exportC);
        }
        return 1;
    }

    @Override
    public int submitPackingList(String packingListId) {
        PackingListC packingListC = new PackingListC();
        packingListC.setPackingListId(packingListId);
        packingListC.setState(1);
        packingListCMapper.updateByPrimaryKeySelective(packingListC);
        return 1;
    }

    @Override
    public void cancelPackingList(String packingListId) {
        PackingListC packingListC = new PackingListC();
        packingListC.setPackingListId(packingListId);
        packingListC.setState(0);
        packingListCMapper.updateByPrimaryKeySelective(packingListC);
    }

}
