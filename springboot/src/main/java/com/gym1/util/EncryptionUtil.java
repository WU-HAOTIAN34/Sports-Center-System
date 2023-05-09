package com.gym1.util;
import java.util.Random;



public class EncryptionUtil {

    public static String encryption(String password){
        int len = password.length();
        Random random = new Random();
        String res = "";
        for (int i = 0; i < len; i++){
            for (int j = 0; j < 5; j++){
                res = res + (char)(random.nextInt(30)+48);
            }
            res = res + password.charAt(i);
        }
        return res;
    }


    public static String decrypt(String password){
        int len = password.length();
        String res = "";
        for (int i = 0; i < len / 6; i++){
            res = res + password.charAt((i+1)*6-1);
        }
        return res;
    }


}
