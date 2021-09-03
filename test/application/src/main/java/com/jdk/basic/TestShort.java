package com.jdk.basic;

/**
 * @author rensong.pu
 * @date 2021/7/27 16:55 星期二
 **/
public class TestShort {
    public static void main(String[] args) {
        short test = 1;
        test = (short) (test + 1); //需要手动转换 int -> short
        System.out.println(test);

        test += 1; // 有隐式short 转换
        System.out.println(test);
    }
}
