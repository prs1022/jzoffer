package com.jzoffer.learn;

import lombok.Data;

/**
 * 输出结果
 * @author rensong.pu
 * @date 2020/4/16 14:47 星期四
 **/
@Data
public class Result {
    private String code;
    private String info;

    public void setResult(String a, String info) {
        this.code = a;
        this.info = info;
    }
}
