package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author rensong.pu
 * @date 2021/8/17 17:58 星期二
 **/
public class single {


    public static void main(String[] args) {
        /**
         * 对某正整数n，如果存在两个正整数a和b，使n=(ab-1)/(a-b)，则称n是“端午数”。问在1到2021的所有自然数中，“端午数”有多少个？
         */
        Set<String> set = new HashSet<String>();
        Map<String, String> map = new HashMap<String, String>();
        for (int n = 1; n <= 2021; n++) {
            for (int i = 2; i <= 2021; i++) {
                for (int j = 1; j < i; j++) {
                    if (n == (i * j - 1) / (i - j) && map.get(String.valueOf(n) )== null) {
//                        System.out.println("i:"+i+",j:"+j+",n:"+n);
                        map.put(String.valueOf(n), "a:" + i + ",b:" + j);
                    }
                }
            }
        }

        for(int i=1;i<=2021;i++){
            System.out.println("n:"+i+"--"+map.get(String.valueOf(i)));
        }
    }
}
