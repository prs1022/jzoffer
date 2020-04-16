package com.jdk.concurrent;/**
 * @author purensong
 * @date 2019/4/10 11:06
 */

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁
 */
@Slf4j
public class LockDeadTest {
    private String lock1 = "lock1";
    private String lock2 = "lock2";

    public static void main(String[] args) throws InterruptedException {
        final LockDeadTest lockDeadTest = new LockDeadTest();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lockDeadTest.lock1) {//锁住lock1，然后获取lock2
                        try {
                            System.out.println(Thread.currentThread() + "获取到lock1");
                            Thread.sleep(1000);//停顿一会时间给t2锁Lock2
                        } catch (InterruptedException e) {
                            log.error("", e);
                        }
                        synchronized (lockDeadTest.lock2) {
                            System.out.println(Thread.currentThread() + "获取到lock2");
                        }
                    }
                }
            }
        }).start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    synchronized (lockDeadTest.lock2) {//锁住lock1，然后获取lock2
                        try {
                            System.out.println(Thread.currentThread() + "获取到lock2");
                            Thread.sleep(100);//停顿一会时间给t2锁Lock2
                        } catch (InterruptedException e) {
                            log.error("", e);
                        }
                        synchronized (lockDeadTest.lock1) {
                            System.out.println(Thread.currentThread() + "获取到lock1");
                        }
                    }
                }
            }
        });
        t2.start();//发生死锁
        Thread.sleep(10000);
        System.out.println("END");
    }

}
