package cn.edu.nju.p.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * helper to encrypt
 */
public class EncryptHelper {

    /**
     * to encrypt the password
     * @param raw the string to be encrypted
     * @return
     */
    public static String getShaEncryption(String raw) {
        String type = "SHA-256";
        String result = Encrypt(raw, type);
        return result;
    }

    /**
     * to check the two password to see whether they are the same
     * @param rawPassword the raw password
     * @param actualPassword the password from database
     * @return
     */
    public static boolean checkPassword(String rawPassword, String actualPassword) {
        String passwordEncrypted = Encrypt(rawPassword, "SHA-256");
        return passwordEncrypted.equals(actualPassword);
    }

    private static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名失败！");
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
