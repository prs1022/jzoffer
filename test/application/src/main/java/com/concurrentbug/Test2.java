package com.concurrentbug;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rensong.pu
 * @date 2021/3/18 15:29 星期四
 **/
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachePool = Executors.newCachedThreadPool();
        cachePool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("cache pool--" + Thread.currentThread().getName());
            }
        });


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("fixed pool--" + finalI + Thread.currentThread().getName() + ",start"+System.currentTimeMillis());
                        Thread.sleep(5000);
                        System.out.println("fixed pool--" + finalI + Thread.currentThread().getName() + ",finish"+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int j = 0; j < 3; j++) {
            int finalJ = j;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("single pool--" + finalJ + Thread.currentThread().getName()+",start"+System.currentTimeMillis());
                        Thread.sleep(5000);
                        System.out.println("single pool--" + finalJ + Thread.currentThread().getName()+",finish"+System.currentTimeMillis());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread.sleep(50000);
        System.exit(0);
    }
}
