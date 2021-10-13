package com.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2021/9/29 14:09 星期三
 **/
public class HashMapTest {
    //    链接：https://zhuanlan.zhihu.com/p/410515217

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(64);
        map.put("alderney", "未实现服务");
        map.put("luminance", "未实现服务");
        map.put("chorology", "未实现服务");
        map.put("carline", "未实现服务");
        map.put("fluorosis", "未实现服务");
        map.put("angora", "未实现服务");
        map.put("insititious", "未实现服务");
        map.put("insincere", "已实现服务");

        // 看看散列到hashmap上的key的hash值，看源码(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
        for(String key:map.keySet()){
            System.out.println((map.size() - 1) & (key.hashCode() ^ (key.hashCode() >>> 16)));
            //全是0
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 实际用到的key排在了链表的最后，每次取值都要循环前面所有用不到的key。低效率的代码，但不易察觉。
            map.get("insincere");
        }
        System.out.println("耗时(initialCapacity)：" + (System.currentTimeMillis() - startTime));
    }
}
