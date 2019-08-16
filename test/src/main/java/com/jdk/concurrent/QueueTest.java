package com.jdk.concurrent;/**
 * @author purensong
 * @date 2019/4/9 17:50
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();//线程安全
        for (int j = 0; j < 3; j++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String take = queue.take();
                        System.out.println(Thread.currentThread() + "取到了数据:"+take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 1; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    queue.add(finalI + "");
                }
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        Thread.sleep(5000);
    }
}
