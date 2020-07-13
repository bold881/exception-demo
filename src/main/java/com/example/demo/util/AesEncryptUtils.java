package com.example.demo.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author daifeng
 */
public class AesEncryptUtils {

    private static final String ivStr = "43720ui239062387";
    public static String encrypt(String strKey, String strIn) throws Exception {
        if(StringUtils.isEmpty(strKey)) {
            throw new Exception("decrypt key can't null or empty");
        }
        if(StringUtils.isEmpty(strIn)) {
            throw new Exception("decrypt string can't null or empty");
        }

        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes("utf-8"));
        return Base64Utils.encodeToString(encrypted);
    }

    public static String decrypt(String strKey, String strIn) throws Exception {
        if(StringUtils.isEmpty(strKey)) {
            throw new Exception("decrypt key can't null or empty");
        }
        if(StringUtils.isEmpty(strIn)) {
            throw new Exception("decrypt string can't null or empty");
        }

        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);


        byte[] encrypted1 = Base64Utils.decodeFromString(strIn);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }
}
