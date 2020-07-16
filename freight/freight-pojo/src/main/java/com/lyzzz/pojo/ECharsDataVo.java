package com.lyzzz.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ECharsDataVo implements Serializable {

    private List<Integer> yData = new ArrayList<>();
    private List<String> xData = new ArrayList<>();

    public void setyData(List<Integer> yData) {
        this.yData = yData;
    }

    public void setxData(List<String> xData) {
        this.xData = xData;
    }

    public List<Integer> getyData() {
        return yData;
    }

    public List<String> getxData() {
        return xData;
    }
}
