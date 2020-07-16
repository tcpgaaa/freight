package com.lyzzz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyzzz.mapper.*;
import com.lyzzz.pojo.*;
import com.lyzzz.service.ExportCService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
@location：  freight\com.lyzzz.service.impl  
@creatTime:   2020/7/10  9:15
@author:  Administrator
@remark:

*/
@Service
public class ExportCServiceImpl implements ExportCService{

    @Resource
    private ExportCMapper exportCMapper;
    @Autowired
    private ContractCMapper contractCMapper;
    @Autowired
    private ContractProductCMapper contractProductCMapper;
    @Autowired
    private ExportProductCMapper exportProductCMapper;
    @Autowired
    private ExtEproductCMapper extEproductCMapper;

    @Override
    public int deleteByPrimaryKey(String exportId) {
        return exportCMapper.deleteByPrimaryKey(exportId);
    }

    @Override
    public int insert(ExportC record) {
        return exportCMapper.insert(record);
    }

    @Override
    public int insertSelective(ExportC record) {
        return exportCMapper.insertSelective(record);
    }

    @Override
    public ExportC selectByPrimaryKey(String exportId) {
        return exportCMapper.selectByPrimaryKey(exportId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExportC record) {
        return exportCMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExportC record) {
        return exportCMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean listExportOfPage(PageBean pageBean, Integer state) {
        //设置查询条件
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());

        List<ExportCVo> exportCVoList = exportCMapper.listExportOfPage(state);

        //获取分页信息
        PageInfo<ExportCVo> pageInfo = new PageInfo<ExportCVo>(exportCVoList);

        pageBean.setTotalPages(pageInfo.getPages());
        pageBean.setDatas(pageInfo.getList());
        pageBean.setTotalRows(pageInfo.getTotal());
        return pageBean;
    }

    @Override
    public void createExportC(ExportC exportC) {
        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        UserP user = currentUser.getUserP();
        DeptP deptP = currentUser.getDeptP();

        //1、新增报运单
        //查询合同信息
        List<String> contractIdList = Arrays.asList(exportC.getContractIds().split(","));//把ids转成list

        List<ContractC> contractCList = contractCMapper.selectByList(contractIdList);

        StringBuffer contractNos = new StringBuffer();
        for (int i = 0; i < contractCList.size(); i++) {
            ContractC contractC =  contractCList.get(i);
            if (StringUtils.isNotBlank(contractNos)) {
                contractNos.append(" ");
            }
            contractNos.append(contractC.getContractNo());
        }
        exportC.setCustomerContract(contractNos.toString());
        exportC.setExportId(UUID.randomUUID().toString());
        exportC.setInputDate(new Date());
        exportC.setState(0);

        exportC.setCreateBy(user.getUserId());
        exportC.setCreateDept(deptP.getDeptId());
        exportC.setCreateTime(new Date());
        exportCMapper.insertSelective(exportC);

        //2、新增报运商品
        //根据合同ids查询合同下的所有货物列表
        String[] contractIds = exportC.getContractIds().split(",");
        List<ContractProductCVo> contractProductCVoList = contractProductCMapper.listContractProductCByContractIds(contractIds);
        for (int i = 0; i < contractProductCVoList.size(); i++) {
            //货物
            ContractProductCVo contractProductCVo =  contractProductCVoList.get(i);

            //商品明细
            ExportProductC exportProductC = new ExportProductC();
            exportProductC.setExportId(exportC.getExportId());
            exportProductC.setExportProductId(UUID.randomUUID().toString());
            //拷贝对象的信息  注意：必须使用org.springframework.beans.BeanUtils，否则BigDecimal类型的数据会报异常
            BeanUtils.copyProperties(contractProductCVo,exportProductC);
            exportProductCMapper.insertSelective(exportProductC);


            //3、新增商品附件
            List<ExtCproductC> extCproductCList = contractProductCVo.getExtCproductCList();
            for (int j = 0; j < extCproductCList.size(); j++) {
                //附件信息
                ExtCproductC extCproductC =  extCproductCList.get(j);

                //商品附件
                ExtEproductC extEproductC = new ExtEproductC();
                extEproductC.setExtEproductId(UUID.randomUUID().toString());
                extEproductC.setExportProductId(exportProductC.getExportProductId());
                //拷贝对象的信息
                BeanUtils.copyProperties(extCproductC,extEproductC);
                extEproductCMapper.insertSelective(extEproductC);
            }

            //4、修改购销合同状态：2
            for (int j = 0; j < contractIds.length; j++) {
                String contractId = contractIds[j];
                ContractC contractC = new ContractC();
                contractC.setState(2);
                contractC.setContractId(contractId);
                contractCMapper.updateByPrimaryKeySelective(contractC);
            }

        }

    }

    @Override
    public void submitExportByExportId(String exportId) {
        ExportC exportC = new ExportC();
        exportC.setExportId(exportId);
        exportC.setState(1);
        exportCMapper.updateByPrimaryKeySelective(exportC);

    }

    @Override
    public void cancelExportByExportId(String exportId) {
        ExportC exportC = new ExportC();
        exportC.setExportId(exportId);
        exportC.setState(0);
        exportCMapper.updateByPrimaryKeySelective(exportC);
    }

}
