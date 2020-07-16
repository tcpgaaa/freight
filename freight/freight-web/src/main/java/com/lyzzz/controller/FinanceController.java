package com.lyzzz.controller;

import com.lyzzz.pojo.FinanceC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.FinanceCService;
import com.lyzzz.service.PackingListCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  14:03
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private PackingListCService packingListCService;

    @Autowired
    private FinanceCService financeCService;


    @RequestMapping("/list")
    public String listPackingListAndParentOfPage(PageBean pageBean, Model model){
        PageBean pb = packingListCService.listPackingListOfPageByState(pageBean);
        model.addAttribute("pb",pb);
        return "cargo/packinglist/jFinanceList";
    }

    @RequestMapping("/finance")
    public String finance(String packingListId,Model model){
        model.addAttribute("packingListId",packingListId);
        return "cargo/packinglist/jFinanceOrder";
    }

    @RequestMapping("/create")
    public String createFinanceC(FinanceC financeC){
        financeCService.createFinanceC(financeC);
        return "redirect:/finance/list";
    }

    @RequestMapping("/submit")
    public String submitFinanceC(String packingListId){
        financeCService.submitFinanceC(packingListId);
        return "redirect:/finance/list";
    }
    @RequestMapping("/cancel")
    public String cancelFinanceC(String packingListId){
        financeCService.cancelFinanceC(packingListId);
        return "redirect:/finance/list";
    }
    @RequestMapping("/toView")
    public String toView(String packingListId, Model model){
        FinanceC financeC = financeCService.selectByPrimaryKey(packingListId);
        model.addAttribute("pack",financeC);
        return "cargo/packinglist/jPackingListView";
    }
}
