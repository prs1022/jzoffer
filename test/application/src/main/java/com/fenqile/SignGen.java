package com.fenqile;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author rensong.pu
 * @date 2019/11/25 15:01 星期一
 **/
public class SignGen {
    public static Map<String, Object> setParam() {
        //请求参数, 采用Tree可以进行排序
        Map<String, Object> data = new TreeMap<>();
        // 公共参数
        data.put("partner_id", "PAI201804190000161");
        data.put("v", "1.4");
        data.put("format", "json");
        String timestamp = Long.toString(System.currentTimeMillis());
        System.out.println(timestamp);
        data.put("timestamp", timestamp);
        data.put("partner_key", "038e490eba3e9c17c680fc59a7cf882d");


        // 业务参数
        data.put("load_amount", "2000");
        data.put("agent", "1064660");
        return data;
    }


    public static String createSign(Map<String, Object> params) {
        // 拼接参数
        String parentKey = "partner_key";
        String parentValue = "";
        if (params.containsKey(parentKey)) {
            parentValue = params.get(parentKey).toString();
            params.remove(parentKey);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            stringBuilder.append(key).append("=").append(value);
            stringBuilder.append("&");
        }
        stringBuilder.append(parentKey).append("=").append(parentValue);
        String paramStr = stringBuilder.toString();
        System.out.println(paramStr);
        // 参数加密
        return DigestUtils.md5Hex(paramStr);
    }

    public static void main(String[] args) {
        System.out.println("sign>>" + createSign(setParam()));
    }
}
