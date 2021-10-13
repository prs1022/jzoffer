package com.lagou;

/**
 * Java 性能优化与面试21讲
 * JIT优化导致不会执行到这里，启动时加上-Djava.compiler=NONE可以禁用JIT
 * @author rensong.pu
 * @date 2020/9/16 16:19 星期三
 **/
public class Main1 {

    static class TestThread extends Thread {
        boolean stop = false;

        private boolean isStop() {
            return stop;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stop = true;
                System.out.println("END");
            }
        }
    }

    public static void main(String[] args) {
        int i = 0;
        TestThread testThread = new TestThread();
        testThread.start();
        while (!testThread.isStop()) {
            i++;
        }
        System.out.println("END-END");

    }
}
