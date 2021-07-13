package com.lagou;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @author rensong.pu
 * @date 2020/9/17 9:44 星期四
 **/
public class Main2 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);


        System.out.println(localDate.getYear()+"--"+localDate.getMonth()+"---"+localDate.getDayOfYear()+"--"+localDate.getDayOfMonth()+"--"+localDate.getDayOfWeek());

        final LocalDate nowDay = LocalDate.of(2020, 9, 17);
        System.out.println(nowDay);

    }
}
