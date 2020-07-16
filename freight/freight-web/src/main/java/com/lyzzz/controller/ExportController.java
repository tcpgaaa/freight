package com.lyzzz.controller;

import com.lyzzz.pojo.ExportC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.ContractCService;
import com.lyzzz.service.ExportCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * 出口报运
 *
 * */
@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ContractCService contractCService;

    @Autowired
    private ExportCService exportCService;

    @RequestMapping("/contractList")
    public String listContractOfPage(PageBean pageBean, String f_type, String f_conditionStr, Model model){
        //查询已上报的合同列表
        PageBean pb = contractCService.listContractOfPage(pageBean,f_type,f_conditionStr,1);
        model.addAttribute("pb",pb);
        //回显查询条件
        model.addAttribute("f_type",f_type);
        model.addAttribute("f_conditionStr",f_conditionStr);
        return "cargo/export/jContractList";
    }

    @RequestMapping("/toCreate")
    public String toCreate(String contractId, Model model){
        model.addAttribute("contractId",contractId);
        return "cargo/export/jExportCreate";
    }

    @RequestMapping("/create")
    public String createExportC(ExportC exportC){
        exportCService.createExportC(exportC);
        return "redirect:/export/contractList";
    }


    @RequestMapping("list")
    public String listDeptAndParentOfPage(PageBean pageBean, Model model, HttpServletRequest request) throws Exception {
        PageBean pb = exportCService.listExportOfPage(pageBean, null);
        model.addAttribute("pb",pb);
        return "cargo/export/jExportList";
    }

    @RequestMapping("/submit")
    public String submit(String exportId){
        exportCService.submitExportByExportId(exportId);
        return "redirect:/export/list";
    }




    @RequestMapping("/toView")
    public String toView(String exportId, Model model){
        ExportC exportC = exportCService.selectByPrimaryKey(exportId);
        model.addAttribute("export",exportC);
        return "cargo/export/jExportView";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String exportId, Model model){
        ExportC exportC = exportCService.selectByPrimaryKey(exportId);
        model.addAttribute("export",exportC);
        return "cargo/export/jExportUpdate";
    }

    @RequestMapping("/update")
    public String update(ExportC exportC){
        exportCService.updateByPrimaryKeySelective(exportC);
        return "redirect:/export/list";
    }

    @RequestMapping("/cancel")
    public String cancel(String exportId){
        exportCService.cancelExportByExportId(exportId);
        return "redirect:/export/list";
    }

    @RequestMapping("/delete")
    public String delete(String exportId){
        exportCService.deleteByPrimaryKey(exportId);
        return "redirect:/export/list";
    }


}
