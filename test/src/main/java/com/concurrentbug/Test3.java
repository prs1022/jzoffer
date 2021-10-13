package com.concurrentbug;

/**
 * @author rensong.pu
 * @date 2021/7/15 17:38 星期四
 **/
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        synchronized (Test3.class) {
            Test3.class.wait();
        }
    }
}
