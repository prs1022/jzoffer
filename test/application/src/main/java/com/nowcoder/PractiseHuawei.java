package com.nowcoder;

import java.util.Scanner;

/**
 * @author rensong.pu
 * @date 2020/3/19 15:53 星期四
 **/
public class PractiseHuawei {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        System.out.println("你输入的字符串为:"+s);
        final String[] s1 = s.split(" ");
        System.out.println("输出结果为:"+s1[s1.length-1].trim().length());
    }
}
