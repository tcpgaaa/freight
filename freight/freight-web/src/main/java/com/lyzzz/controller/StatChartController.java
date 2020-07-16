package com.lyzzz.controller;

import com.lyzzz.pojo.ECharsData;
import com.lyzzz.pojo.ECharsDataVo;
import com.lyzzz.service.StatChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/13  9:50
 * @author: Administrator
 * @remark:
 */
@Controller
@RequestMapping("/statChart")
public class StatChartController {
    @Autowired
    private StatChartService statChartService;

    @RequestMapping("/factorySale")
    public String toFactorySale(){
        return "stat/chart/SaleView";
    }
    // 厂家销售
    @RequestMapping("/listFactorySaleData")
    @ResponseBody
    public List<ECharsData> listFactorySaleData(){
        return statChartService.listFactorySaleData();
    }

    @RequestMapping("/productSale")
    public String toProductSale(){
        return "stat/chart/SaleOrder";
    }
    // 产品销售
    @RequestMapping("/listProductSaleData")
    @ResponseBody
    public ECharsDataVo listProductSaleData(){
        List<ECharsData> eCharsData = statChartService.listProductSaleData();
        ECharsDataVo productSaleData = new ECharsDataVo();
        for (int i = 0; i < eCharsData.size(); i++) {
            ECharsData charsData =  eCharsData.get(i);
            productSaleData.getxData().add(charsData.getName());
            productSaleData.getyData().add(charsData.getValue());
        }
        return productSaleData;
    }


    @RequestMapping("/onlineInfo")
    public String toOnlineInfo(){
        return "stat/chart/UserBrowse";
    }

    @RequestMapping("/listAccessLogData")
    @ResponseBody
    public ECharsDataVo listAccessLogData(){
        List<ECharsData> eCharsData = statChartService.listAccessLogData();
        ECharsDataVo productSaleData = new ECharsDataVo();
        for (int i = 0; i < eCharsData.size(); i++) {
            ECharsData charsData =  eCharsData.get(i);
            productSaleData.getxData().add(charsData.getName());
            productSaleData.getyData().add(charsData.getValue());
        }

        System.out.println(productSaleData);


        return productSaleData;
    }



}
