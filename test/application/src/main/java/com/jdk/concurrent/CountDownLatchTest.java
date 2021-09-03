package com.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author rensong.pu
 * @date 2021/7/14 14:26 星期三
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized (CountDownLatchTest.class) {
                        System.out.println(Thread.currentThread().getName()+"---"+countDownLatch.getCount());
                        countDownLatch.countDown();
                        System.out.println(Thread.currentThread().getName()+"---"+countDownLatch.getCount());
                    }
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("执行完成");

    }
}
