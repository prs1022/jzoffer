package com.java8;

/**
 * @author rensong.pu
 * @date 2020/7/29 17:05 星期三
 **/
@FunctionalInterface
public interface MyInterface {
    double calculate(int a);

//    double sum(int a,int b);

    default double sqrt(int a){
        return Math.sqrt(a);
    }

    static double sqrt2(int b){
        return Math.sqrt(b);
    }
}
