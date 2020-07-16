package com.lyzzz.controller;

import com.lyzzz.pojo.ContractProductC;
import com.lyzzz.pojo.FactoryC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.ContractProductCService;
import com.lyzzz.service.FactoryCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/contractProduct")
public class ContractProductController {

    @Autowired
    private ContractProductCService contractProductCService;
    @Autowired
    private FactoryCService factoryCService;

    @RequestMapping("/toCreate")
    public String toCreate(PageBean pageBean, String contractId, Model model){
        //查询当前合同的货物列表
        PageBean pb = contractProductCService.listContractProductOfPage(pageBean,contractId);
        model.addAttribute("pb",pb);

        //查询厂家下拉数据
        List<FactoryC> factoryCList = factoryCService.listFactory();
        model.addAttribute("factoryCList",factoryCList);

        //回显合同主键
        model.addAttribute("contractId",contractId);
        return "cargo/contract/jContractProductCreate";
    }

    @RequestMapping("/create")
    public String createContractProduct(ContractProductC contractProductC){
        contractProductCService.createContractProduct(contractProductC);
        return "redirect:/contractProduct/toCreate?contractId="+contractProductC.getContractId();
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id,Model model){
        ContractProductC contractProductC = contractProductCService.selectByPrimaryKey(id);
        model.addAttribute("cpc",contractProductC);
        // 生产厂家
        List<FactoryC> factoryCList = factoryCService.listFactory();
        model.addAttribute("fs",factoryCList);

        return "cargo/contract/jContractProductUpdate";
    }

    @RequestMapping("/update")
    public String update(ContractProductC contractProductC){
        contractProductCService.updateByPrimaryKeySelective(contractProductC);
        return "redirect:/contractProduct/toCreate?contractId="+contractProductC.getContractId();
    }

    @RequestMapping("/delete")
    public String delete(String id, String cid){
        // 删除货物
        int i = contractProductCService.delete(id,cid);
        // 修改合同金额
        return "redirect:/contractProduct/toCreate?contractId="+cid;
    }

}
