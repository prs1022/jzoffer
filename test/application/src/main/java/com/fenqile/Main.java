package com.fenqile;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2019/11/25 15:16 星期一
 **/
public class Main {

    @Test
    public void register(){
        String url = "https://open.api.fenqile.com/route0007/user/api/access.json";
        HashMap<String, Object> params = new HashMap<>();
        Map<String,String> plainTxt = new HashMap<>();
        plainTxt.put("mobile","15251710377");
        plainTxt.put("third_uid","123");
        plainTxt.put("client_id","123");
        params.put("encryptedData",AESUtil.stringEncrypt(new Gson().toJson(plainTxt),"123"));
        params.put("agent","1082468");
        final String s = HttpUtils.doFormPost(params, url);
        System.out.println(s);
    }
}
