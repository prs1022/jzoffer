package com.nowcoder;

import java.util.Scanner;

/**
 * @author rensong.pu
 * @date 2020/3/19 16:07 星期四
 **/
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String str = scanner.nextLine().toLowerCase();
        final String subStr = scanner.nextLine().toLowerCase();
        final String[] split = str.split(subStr);
        if (split.length == 1) {
            System.out.println(0);
        } else {
            System.out.println(split.length - 1);
        }
    }
}
