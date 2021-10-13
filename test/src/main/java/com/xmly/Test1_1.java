package com.xmly;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 两个线程，一个数字，一个字母，交替输出1A2B...26Z
 *
 * @author rensong.pu
 * @date 2021/7/29 17:23 星期四
 **/
public class Test1_1 {
    public static volatile Boolean flag = Boolean.TRUE;

    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new NumberPrintThread());
            System.out.println("1111");
            System.out.println(flag);
            executorService.execute(new AlphabetPrintThread());
            System.out.println("222");
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
        }
    }
}