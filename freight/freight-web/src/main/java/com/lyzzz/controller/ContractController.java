package com.lyzzz.controller;

import com.alibaba.excel.EasyExcel;
import com.lyzzz.pojo.ContractC;
import com.lyzzz.pojo.ContractCVo;
import com.lyzzz.pojo.OutProduct;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.ContractCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/9  14:16
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractCService contractCService;

    @RequestMapping("/list")
    public String listContractOfPage(PageBean pageBean,String f_type,String f_conditionStr, Model model, HttpServletRequest request) throws Exception {
        PageBean pb = contractCService.listContractOfPage(pageBean,f_type,f_conditionStr, null);
        model.addAttribute("pb",pb);
        //回显查询条件
        model.addAttribute("f_type",f_type);
        model.addAttribute("f_conditionStr",f_conditionStr);
        return "cargo/contract/jContractList";
    }


    @RequestMapping("/toCreate")
    public String tocreate(){
        return "cargo/contract/jContractCreate";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createContractC(ContractC contractC){
        System.out.println(contractC);

        contractCService.createContractC(contractC);
        return "redirect:/contract/list";
    }

    @RequestMapping("/submit")
    public String submit(String contractId){
        contractCService.submitContractCByContractId(contractId);
        return "redirect:/contract/list";
    }
    @RequestMapping("/cancel")
    public String cancel(String contractId){
        contractCService.cancelContractCByContractId(contractId);
        return "redirect:/contract/list";
    }

    @RequestMapping("/toView")
    public String toView(String contractId,Model model){
        ContractCVo contractCVo = contractCService.viewContractCByContractId(contractId);
        model.addAttribute("contractCVo",contractCVo);
        return "cargo/contract/jContractView";
    }
    @RequestMapping("/delete")
    public String delete(String contractId){
        int delete = contractCService.deleteByPrimaryKey(contractId);

        return "redirect:/contract/list";
    }
    @RequestMapping("/print")
    public String print(String contractId,HttpServletResponse response) throws IOException {
        ContractCVo contractCVo = contractCService.viewContractCByContractId(contractId);
        ArrayList<ContractCVo> contractCVos = new ArrayList<>();
        contractCVos.add(contractCVo);
        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("报运单", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), ContractCVo.class).sheet("sheet1").doWrite(contractCVos);
        return "redirect:/contract/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String contractId, Model model) {
        ContractC contractC = contractCService.selectByPrimaryKey(contractId);
        model.addAttribute("c",contractC);

        return "cargo/contract/jContractUpdate";
    }

    @RequestMapping("/update")
    // 修改
    public String update(ContractC contractC) {
        int update = contractCService.updateByPrimaryKeySelective(contractC);
//        //重新查询最新的数据
        return "redirect:/contract/list";
    }

    @RequestMapping("/exportContract")
    public void exportDept(HttpServletResponse response) throws IOException {
        List<ContractC> datas = contractCService.selectAll();

        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("合同信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), ContractC.class).sheet("sheet1").doWrite(datas);

    }


    //跳转出货表页
    @RequestMapping("/toEdit")
    public String toEdit(){
        return "cargo/outproduct/jOutProduct";
    }

    @RequestMapping("/outProductPrint")
    public void outProductPrint(String inputDate, HttpServletResponse response) throws IOException {
        //1、查询数据
        List<OutProduct> datas = contractCService.listOutProductByMonth(inputDate);

        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("出货表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), OutProduct.class).sheet("sheet1").doWrite(datas);

    }





}
