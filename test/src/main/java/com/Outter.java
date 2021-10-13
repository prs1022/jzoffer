package com;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author rensong.pu
 * @date 2021/1/22 15:44 星期五
 **/
public class Outter {
    private Integer  a;
    Integer  b;
    public  Integer c;
    class Inner{
        void print(){
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
//        Inner in = new Outter().new Inner();
//        in.print();
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        Integer[] a =set.toArray(new Integer[0]);

        for(int i = 0; i < 100; i++) {
            System.out.println(a[i]);
        }
    }
}
