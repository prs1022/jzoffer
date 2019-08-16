package com.jdk.concurrent;/**
 * @author purensong
 * @date 2019/4/10 14:22
 */

import com.jdk.collection.map.MyHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashMapTest {
    public static void main(String[] args) {

    }

    public void test(){
        MyHashMap map = new MyHashMap();
        for (int i = 0; i < 1000; i++) {
            map.put(i + "", Math.random() * 100);
        }

        for(int j=0;j<1001;j++){
            System.out.println(map.get(j+""));
        }
    }

}
