package com.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 与 countdown区别是可以反复使用，所有线程彼此等待达到num数就一起执行。
 * @author rensong.pu
 * @date 2021/7/14 14:46 星期三
 **/
public class CycliBarriarTest {
    static final int num = 2;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        final ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i = 0; i < 6; i++) {
            System.out.println(i+"开始");
            executorService.execute(new MyRunnable(cyclicBarrier, i));
//            Thread.sleep(500);
        }
        System.out.println("主线程结束");
    }
}

class MyRunnable implements Runnable {
    int name;
    CyclicBarrier cyclicBarrier;

    public MyRunnable(CyclicBarrier cyclicBarrier, int name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程" + name + "--开始阻塞--barriar大小:"+cyclicBarrier.getNumberWaiting());
            System.out.println("线程"+name +"开始冲刺栅栏A");
            cyclicBarrier.await();
            System.out.println("线程"+name +"冲完 栅栏A!!");
            Thread.sleep(1000);

            System.out.println("线程"+name +"开始冲刺栅栏B");
            cyclicBarrier.await();
            System.out.println("线程"+name +"冲完 栅栏B!!");
            System.out.println("线程" + name + "--执行！--barriar大小:"+cyclicBarrier.getNumberWaiting());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
