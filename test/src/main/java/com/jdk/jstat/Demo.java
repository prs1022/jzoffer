package com.jdk.jstat;

import java.util.HashMap;
import java.util.Map;

/**
 * jdk工具jmap，jstat内存分析demo
 * @author rensong.pu
 * @date 2019/11/21 9:59 星期四
 **/
public class Demo {
    static class A{
//        private Map<String,Object> map = new HashMap<>(1000);
        private byte[] arr = new byte[1024];
    }
    public static void main(String[] args) throws InterruptedException {
        while(true){
            final A a = new A();
            Thread.sleep(100);
        }
    }
}
