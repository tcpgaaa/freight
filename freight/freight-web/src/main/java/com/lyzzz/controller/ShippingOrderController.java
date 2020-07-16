package com.lyzzz.controller;

import com.lyzzz.pojo.PageBean;
import com.lyzzz.pojo.ShippingOrderC;
import com.lyzzz.service.PackingListCService;
import com.lyzzz.service.ShippingOrderCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  14:32
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/shippingOrder")
public class ShippingOrderController {
    @Autowired
    private PackingListCService packingListCService;

    @Autowired
    private ShippingOrderCService shippingOrderCService;


    @RequestMapping("/list")
    public String listPackingListAndParentOfPage(PageBean pageBean, Model model){
        PageBean pb = packingListCService.listPackingListOfPageByState(pageBean);
        model.addAttribute("pb",pb);
        return "cargo/packinglist/jShippingList";
    }


    @RequestMapping("/submit")
    public String submitShippingOrder(String packingListId){
        shippingOrderCService.submitShippingOrder(packingListId);
        return "redirect:/shippingOrder/list";
    }
    @RequestMapping("/cancel")
    public String cancelShippingOrder(String packingListId){
        shippingOrderCService.cancelShippingOrder(packingListId);
        return "redirect:/shippingOrder/list";
    }

    // 委托
    @RequestMapping("/entrust")
    public String entrust(String packingListId,Model model){
        model.addAttribute("packingListId",packingListId);
        return "cargo/packinglist/jShippingOrder";
    }

    @RequestMapping("/create")
    public String createShippingOrder(ShippingOrderC shippingOrderC){
        System.out.println(shippingOrderC);
        shippingOrderCService.createShippingOrder(shippingOrderC);
        return "redirect:/shippingOrder/list";
    }

    @RequestMapping("/toView")
    public String toView(String packingListId, Model model){
        ShippingOrderC shippingOrderC = shippingOrderCService.selectByPrimaryKey(packingListId);
        model.addAttribute("pack",shippingOrderC);
        return "cargo/packinglist/jPackingListView";
    }


}
