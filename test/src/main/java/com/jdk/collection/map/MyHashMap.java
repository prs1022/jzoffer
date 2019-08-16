package com.jdk.collection.map;/**
 * @author purensong
 * @date 2019/4/10 14:55
 */

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int initArrSize = 1 << 2;//初始数组大小

    private static final float yuzhi_factor = 0.75f;//扩容阈值比例

    private int entryUseSize;//Map中的entry数量

    private MyEntry<K, V>[] table = null;

    public MyHashMap() {//这里省略掉自定义map大小的构造方法
        this.table = new MyEntry[initArrSize];
    }

    public class MyEntry<K, V> implements Entry<K, V> {

        private K key;
        private V val;
        private MyEntry<K, V> next;

        public MyEntry() {
        }

        public MyEntry(K key, V val, MyEntry next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }
    }

    public V put(K k, V v) {
        V oldValue = null;
        //扩容
        //使用大小超过默认大小
        if (entryUseSize >= initArrSize * yuzhi_factor) {
            resize(2 * initArrSize);
        }
        //做hash
        int index = hash(k) & (initArrSize - 1);
        //单链表插入
        if (table[index] == null) {
            table[index] = new MyEntry<K, V>(k, v, null);
            entryUseSize++;
        } else {
            MyEntry<K, V> entry = table[index];
            MyEntry<K, V> tmp = entry;
            while (tmp != null) {
                if (entry.getKey() == k || entry.getKey().equals(k)) {
                    oldValue = tmp.val;
                    tmp.val = v;
                    return oldValue;
                }
                tmp = tmp.next;
            }
            table[index] = new MyEntry<K, V>(k, v, entry); //生成一个新entry
            entryUseSize++;
        }
        return oldValue;
    }

    /**
     * hash算法
     *
     * @param k
     * @return
     */
    private int hash(K k) {
        //todo 参照HashMap的hash
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    /**
     * 扩容
     *
     * @param size 重新分配大小
     */
    private void resize(int size) {
        MyEntry[] newTable = new MyEntry[size];
        initArrSize = size;//改变了初始数组的大小
        entryUseSize = 0;//初始化entry个数为0
        rehash(newTable);
        System.out.println("扩容后的大小：" + size);
    }

    /**
     * 重新hash，即将table中的entry放到newTable中
     *
     * @param newTable
     */
    private void rehash(MyEntry[] newTable) {
        ArrayList<MyEntry<K, V>> list = new ArrayList<MyEntry<K, V>>();
        for (MyEntry item : table) {
            if (item != null) {
                do {
                    list.add(item);
                    item = item.next;
                } while (item != null);
            }
        }
        table = newTable;
        for (int i = 0; i < list.size(); i++) {
            put(list.get(i).key, list.get(i).val);
        }
    }

    public V get(K k) {
        int index = hash(k) & (initArrSize - 1);
        if (table[index] == null) {
            return null;
        } else {
            MyEntry<K, V> kvMyEntry = table[index];
            do {
                if (k == kvMyEntry.key || k.equals(kvMyEntry.key)) {
                    return kvMyEntry.val;
                }
                kvMyEntry = kvMyEntry.next;
            } while (kvMyEntry != null);
        }
        return null;
    }
}
