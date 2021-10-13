package com.jdk.basic;

/**
 * @author rensong.pu
 * @date 2021/7/27 16:57 星期二
 **/
public class TestGoto {
    public static void main(String[] args) {
        label:
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                int a = i;
                int b = i + 1;
                System.out.println("a+b=" + (a + b));
                if (a == 3) {
                    break label;
                }
            }
            System.out.println("j=" + j);
        }
        System.out.println("finish");
    }
}
