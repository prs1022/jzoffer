//package com.fenqile;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author rensong.pu
// * @create 2019年10月14日 10:53
// */
//@Slf4j
//public class HttpUtils {
//
//    private static final int CONNECT_TIMEOUT = 5000;
//    private static final int SOCKET_TIMEOUT = 10000;
//    /**
//     * 重试次数
//     */
//    private static final int RETRY_TIMES = 3;
//
//    public static String doPost(Map<String, Object> params, String url) {
//        //失败重试
//        return doPost(params, null, url, "application/x-www-form-urlencoded;charset=UTF-8", RETRY_TIMES);
//    }
//
//    public static String doPost(String reqEntity, String url) {
//        return doPost(null, reqEntity, url, "application/json", RETRY_TIMES);
//    }
//
//    public static String doFormPost(Map<String, Object> params, String url) {
//        return doPost(params, null, url, "application/x-www-form-urlencoded;charset=UTF-8", RETRY_TIMES);
//    }
//
//    /**
//     * POST请求方式
//     *
//     * @param params
//     * @param url
//     */
//    public static String doPost(Map<String, Object> params, String reqEntity, String url, String contentType, int retryTime) {
//        String result = "";
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost(url);
//        RequestConfig config = RequestConfig.custom()
//                .setConnectTimeout(CONNECT_TIMEOUT)
//                .setSocketTimeout(SOCKET_TIMEOUT)
//                .build();
//        httppost.setConfig(config);
//        // 创建参数队列
//        List<NameValuePair> formparams = new ArrayList<>();
//        if (params != null && params.size() > 0) {
//            for (Map.Entry<String, Object> en : params.entrySet()) {
//                if (en.getValue() != null) {
//                    formparams.add(new BasicNameValuePair(en.getKey(), en.getValue().toString()));
//                }
//            }
//        }
//        HttpEntity entity = null;
//        try {
//            if (params != null && params.size() > 0) {
//                entity = new UrlEncodedFormEntity(formparams, "UTF-8");
//                ((UrlEncodedFormEntity) entity).setContentType(contentType);
//            }
//            if (reqEntity != null && reqEntity.length() > 0) {
//                entity = new StringEntity(reqEntity, "UTF-8");
//                ((StringEntity) entity).setContentType(contentType);
//            }
//            httppost.setEntity(entity);
//
//            log.info("executing request " + httppost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity responseEntity = response.getEntity();
//                if (responseEntity != null) {
//                    result = EntityUtils.toString(responseEntity, "UTF-8");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (IOException e) {
//            log.error("", e);
//            if (retryTime > 0) {
//                log.info("重试第" + (RETRY_TIMES - retryTime + 1) + "次");
//                doPost(params, reqEntity, url, contentType, --retryTime);
//            }
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                log.error("", e);
//                if (retryTime > 0) {
//                    log.info("重试第" +  (RETRY_TIMES - retryTime + 1) + "次");
//                    doPost(params, reqEntity, url, contentType, --retryTime);
//                }
//            }
//        }
//        return result;
//    }
//
//}
