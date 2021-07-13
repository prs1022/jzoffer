package com.egao;

import java.io.IOException;
import java.util.Stack;

/**
 * 恶搞
 * @author rensong.pu
 * @date 2020/8/27 16:43 星期四
 **/
public class Egao {

    public synchronized void sayHello(){
        System.out.println("hello");
        Object o = new Egao();
        synchronized (o){
            System.out.println("同步方法块");
        }
    }



    private static volatile String flag = "12";



    public static void main(String[] args) throws IOException {
        if(flag=="11"){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
        //        Runtime.getRuntime().exec(System.getenv("windir")+"\\system32\\shutdown.exe -s -t 10");
//        new Egao().sayHello();
//        int i=1;
//        i++;
//        System.out.println(i);
//
//
    }

}
