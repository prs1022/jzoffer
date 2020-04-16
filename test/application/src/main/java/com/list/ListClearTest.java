package com.list;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author purensong
 * @date 2019/8/7 16:22
 */
@Slf4j
public class ListClearTest {
    public static void main(String[] args) throws InterruptedException, IOException {
//        List<List<String>> list = new ArrayList<>();
//        String fiveKbText = getFiveKbText();
//        List<String> tmp = new ArrayList<>();
//        for (int i = 0; i < 10000000; i++) {
//            for (int j = 0; j < 100; j++) {
//                tmp.add(fiveKbText);
//            }
//            list.add(tmp);
//            tmp.clear();
//            Thread.sleep(5);
//            System.out.println("size:" + list.size());
//        }
//        String pageCode = "page_5f1656531a51445e8751950df268ce91,page_5f1656531a51445e8751950df268ce91";
        int i = 0;
        i=i++;
        System.out.println(i);
    }

    private static String getFiveKbText() throws IOException {
        InputStream resourceAsStream = ListClearTest.class.getClassLoader().getResourceAsStream("./replay_pid25336.log");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
