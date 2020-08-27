package com.concurrentbug;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * SimpleDateFormat多线程下会有安全问题
 * @author rensong.pu
 * @date 2020/8/26 17:48 星期三
 **/
public class Test1 {
    private ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Test1 test1 = new Test1();
        for(int i=0;i<30;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        System.out.println(test1.dateFormat.parse("2020-08-26 17:58:11"));
                    try {
                        System.out.println(test1.dateFormatThreadLocal.get().parse("2020-08-26 17:58:11"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                        System.out.println(test1.dateFormat.format(new Date()));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }

    }
}
