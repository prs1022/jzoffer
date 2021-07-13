package com.java15;

/**
 * @author rensong.pu
 * @date 2020/9/21 14:09 星期一
 **/
public class Feature1 {
    /**
     * instance of
     *
     * @param args
     */
    public static void main(String[] args) {
        Car car = new Car();
//        if (car instanceof Tool t){
//            t.tun();
//        }
    }

    static class Tool {
        protected void run() {
            System.out.println("run ");
        }
    }

    static class Car extends Tool {
        private String band;
        private long price;
    }
}
