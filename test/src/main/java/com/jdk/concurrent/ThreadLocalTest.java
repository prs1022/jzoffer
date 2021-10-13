package com.jdk.concurrent;

/**
 * @author rensong.pu
 * @date 2021/9/24 16:29 星期五
 **/
public class ThreadLocalTest {
    public static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        threadLocal.set("123");
    }
}
