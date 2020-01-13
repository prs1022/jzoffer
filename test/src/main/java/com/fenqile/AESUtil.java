package com.fenqile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 浣跨敤AES绠楁硶鐨勫姞瀵嗗伐鍏风被
 *
 * @author: zeki
 * @date: 2019/07/31 15:36
 **/
public class AESUtil {

    private static Logger logger = LoggerFactory.getLogger(AESUtil.class);


    public static String stringEncrypt(String content, String password) {
        byte[] encryptByteData = encrypt(content, password);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(encryptByteData);
    }

    private static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    //用户敏感信息加密
    public static void main(String[] args) {
        String partnerKey = "a15087d6f156b951667bcdbff481f687";
//        SecretKey secretKey = keyGenerator("AES", "SHA1PRNG", 128, partnerKey);
        String jsonStr = "{\"name\":\"王晓晓\",\"mobile\":\"18812345678\",\"identity\":\"18054519871231245X\"}";
        byte[] bytes = encrypt(jsonStr, partnerKey);
        final byte[] encode = Base64.getEncoder().encode(bytes);
        try {
            final String utf8 = new String(encode, "utf8");
            System.out.println(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static SecretKey keyGenerator(String aes, String sha1PRNG, int i, String partnerKey) {
        //todo 需要分期乐提供
        return null;
    }

}