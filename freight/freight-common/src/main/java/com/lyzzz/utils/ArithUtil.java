package com.lyzzz.utils;

import java.math.BigDecimal;

public class ArithUtil {

    // 不能实例化
    private ArithUtil() {
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));// 建议写string类型的参数，下同
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    private static double div(double v1, double v2, int scale) {
        if (scale <= 0) {
            throw new IllegalArgumentException(" the scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();// scale 后的四舍五入
    }

}
