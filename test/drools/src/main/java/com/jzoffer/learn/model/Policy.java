package com.jzoffer.learn.model;

import lombok.Data;

/**
 * FACT 对象
 *
 * @author rensong.pu
 * @date 2020/4/16 14:47 星期四
 **/
@Data
public class Policy {
    /**
     * 性别；男、女
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 单身；是/否
     */
    private Boolean userSingle;
    /**
     * 结婚；是/否
     */
    private Boolean userMarry;
    /**
     * 育儿；是/否
     */
    private Boolean userParenting;
}
