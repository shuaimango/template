package com.example.mylibrary.util;

import java.math.BigDecimal;

public class Util_calculate {

    /**
     * 指定几位小数点
     */
    public static double getExpectFloat(double f,int i){
        int digit= (int) Math.pow(10,i);//10的i次方
        double   b   =   f<=0?0:(double)(Math.round(f*digit))/digit;//(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)
        return b;
    }


    /**
     * @param m1
     * @param m2
     * @return
     */
    public static double add(String m1, String m2) {
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.add(p2).doubleValue();
    }
    /**
     * @param m1
     * @param m2
     * @return
     */
    public static double add(double m1, double m2) {
        return add(String.valueOf(m1), String.valueOf(m2));
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static double sub(String m1, String m2) {
        BigDecimal p1 = new BigDecimal(m1);
        BigDecimal p2 = new BigDecimal(m2);
        return p1.subtract(p2).doubleValue();
    }
    /**
     * @param m1
     * @param m2
     * @return
     */
    public static double sub(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static double multiply(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).doubleValue();
    }

    /**
     */
    public static double get1double(String doubleValue) {
        return get1double(Util_str.parseDouble(doubleValue));
    }
    public static double get1double(double doubleValue) {
        return get1double(doubleValue,1);
    }
    public static double get1double(double doubleValue,double fenmu) {
        return Util_calculate.div(doubleValue,fenmu,1);
    }
    /**
     *   @param   m1
     *   @param   m2
     *   @param   scale
     *   @return
     */
    public static double div(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        if(m2==0){
            return 0;
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        double doubleValue = p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return doubleValue;
    }
}
