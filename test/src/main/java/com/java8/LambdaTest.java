package com.java8;

/**
 * @author rensong.pu
 * @date 2019/12/10 14:16 星期二
 **/
public class LambdaTest {
    public enum Operation {
        PLUS("+", (x, y) -> x + y),
        MINUS("-", (x, y) -> x - y);
        private String opt;
        private Caculator ca;

        Operation(String opt, Caculator ca) {
            this.opt = opt;
            this.ca = ca;
        }

        public double apply(double x, double y) {
            return ca.caculate(x, y);
        }
    }

    public static void main(String[] args) {
        double x = 1.1;
        double y = 0.9;
        System.out.println(Operation.MINUS.apply(x,y));
        System.out.println(Operation.PLUS.apply(x,y));
    }
}
