package com.concurrentbug.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rensong.pu
 * @date 2021/7/14 16:51 星期三
 **/
public class AtomicTest {

    /**
     * atomic包下都是乐观锁
     * CAS
     */
    public static void main(String[] args) {
        AtomicInteger atomic = new AtomicInteger(1);
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    atomic.incrementAndGet();
                    System.out.println(atomic.get());
                }
            });
        }

        System.out.println(atomic.get());
    }
}
