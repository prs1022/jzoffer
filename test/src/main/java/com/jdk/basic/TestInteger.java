package com.jdk.basic;

import java.lang.reflect.Field;

/**
 * @author rensong.pu
 * @date 2021/8/5 15:31 星期四
 **/
public class TestInteger {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = cache.getDeclaredField("cache"); //2
        myCache.setAccessible(true);//3

        Integer[] newCache = (Integer[]) myCache.get(cache); //4
        newCache[132] = newCache[133]; //5 132对应的-128+132 = 4，就是4和5的值发生了互换，如果得到4，显示5，如果得到5显示4

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }
}
