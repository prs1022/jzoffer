package com.jdk.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author purensong
 * @date 2019/4/9 13:09
 */

public class Main {

//    private AtomicInteger count = new AtomicInteger(0);//原子性

    private int count = 0;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Lock lock = new MyLock();

    public void add() {
        lock.lock();
//        count.addAndGet(1);
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
//        int i =0;
//        int k = 10;
//        while(k-->0) {
//            System.out.println(i);
//            i++;
//        }
//        System.out.println(i);

        final Main main = new Main();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        main.add();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(main.count);
    }
}
