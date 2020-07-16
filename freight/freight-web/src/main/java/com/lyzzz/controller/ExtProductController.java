package com.lyzzz.controller;

import com.lyzzz.pojo.ExtCproductC;
import com.lyzzz.pojo.FactoryC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.ContractProductCService;
import com.lyzzz.service.ExtCproductCService;
import com.lyzzz.service.FactoryCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  15:37
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/extCproduct")
public class ExtProductController {
    @Autowired
    private FactoryCService factoryCService;
    @Autowired
    private ExtCproductCService extCproductCService;
    @Autowired
    private ContractProductCService contractProductCService;

    @RequestMapping("/toCreate")
    public String toCreate(PageBean pageBean, String cid, String cpid, Model model){
        //查询厂家下拉数据
        List<FactoryC> factoryCList = factoryCService.listFactory();
        model.addAttribute("fs",factoryCList);

        //查询当前货物的附件列表
        PageBean pb = extCproductCService.selectAll(pageBean,cid);
        model.addAttribute("pb",pb);

        //回显合同、货物主键
        model.addAttribute("contractId",cid);
        model.addAttribute("contractProductId",cpid);
        return "cargo/contract/jExtCproductCreate";
    }
    @RequestMapping("/create")
    public String create(String cid, ExtCproductC extCproductC){
        extCproductCService.createExtCproductC(extCproductC,cid);

        return "redirect:/contractProduct/toCreate?cid="+cid;
    }

}
