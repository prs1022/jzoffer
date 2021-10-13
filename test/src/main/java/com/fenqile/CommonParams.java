//package com.fenqile;
//
//import com.google.gson.annotations.SerializedName;
//import com.sun.istack.internal.NotNull;
//
///**
// * @author rensong.pu
// * @date 2019/11/25 15:19 星期一
// **/
//public class CommonParams {
//    /**
//     * 合作伙伴身份标识
//     */
//    @SerializedName("partner_id")
//    @NotNull
//    String partnerId;
//
//    /**
//     * 当前时间戳，允许请求误差5分钟
//     */
//    @NotNull
//    private String timestamp;
//
//    /**
//     * 响应格式
//     */
//    private String format = "json";
//
//    /**
//     * API协议版本
//     */
//    @NotNull
//    private String v = "1.4";
//
//    /**
//     * 签名有效期5分钟，超过五分钟需要重新签名
//     */
//    @NotNull
//    private String sign;
//
//}
