package com.yuqiqi.leetcode.linshan.basic;

public class ConvertTemperature {
    public double[] convertTemperature(double celsius) {
//开氏度 = 摄氏度 + 273.15
//华氏度 = 摄氏度 * 1.80 + 32.00
        double k = celsius + 273.15;
        double h = celsius * 1.80 + 32.00;
        return new double[]{k , h};
    }
}
