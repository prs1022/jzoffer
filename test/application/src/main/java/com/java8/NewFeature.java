package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author rensong.pu
 * @date 2020/7/29 17:05 星期三
 **/
public class NewFeature {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface() {
            @Override
            public double calculate(int a) {
                return 0;
            }
        };
        Function<Integer, Double> sqrr = myInterface::sqrt;
        final Double apply = sqrr.andThen(aDouble -> {
            return aDouble+aDouble;
        }).apply(9);
//        System.out.print(apply);

        int b = 3;
        MyInterface myInterface1 = a -> Math.sqrt(a+b);
//        b = 4; //即使没有定义b为final，但是用到了lambda表达式，即视为final
        System.out.println(myInterface1.calculate(4));

        List list = new ArrayList();
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.add("hello");
        list.forEach(System.out::println);
    }
}
