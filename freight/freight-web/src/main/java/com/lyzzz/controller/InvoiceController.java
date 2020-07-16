package com.lyzzz.controller;

import com.lyzzz.pojo.InvoiceC;
import com.lyzzz.pojo.PageBean;
import com.lyzzz.service.InvoiceCService;
import com.lyzzz.service.PackingListCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  11:31
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private PackingListCService packingListCService;
    @Autowired
    private InvoiceCService invoiceCService;

    @RequestMapping("/list")
    public String listPackingListAndParentOfPage(PageBean pageBean, Model model){
        PageBean pb = packingListCService.listPackingListOfPageByState(pageBean);
        pb.getDatas().stream()
                .forEach(System.out::println);
        System.out.println("发票"+pb);
        model.addAttribute("pb",pb);
        return "cargo/packinglist/jInvoiceList";
    }

    @RequestMapping("/invoice")
    public String invoice(String packingListId,Model model){
        model.addAttribute("packingListId",packingListId);
        return "cargo/packinglist/jInvoiceOrder";
    }

    @RequestMapping("/create")
    public String createInvoice(InvoiceC invoiceC){
        invoiceCService.createInvoice(invoiceC);
        return "redirect:/invoice/list";
    }

    @RequestMapping("/submit")
    public String submitInvoice(String packingListId) throws Exception {
        invoiceCService.submitInvoice(packingListId);
        return "redirect:/invoice/list";
    }

    @RequestMapping("/cancel")
    public String cancelFinanceC(String packingListId){
        invoiceCService.cancelFinanceC(packingListId);
        return "redirect:/invoice/list";
    }
    @RequestMapping("/toView")
    public String toView(String packingListId, Model model){
        InvoiceC invoiceC = invoiceCService.selectByPrimaryKey(packingListId);
        model.addAttribute("pack",invoiceC);
        return "cargo/packinglist/jPackingListView";
    }


}
