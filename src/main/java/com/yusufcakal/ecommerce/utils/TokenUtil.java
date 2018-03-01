package com.yusufcakal.ecommerce.utils;

import java.util.Random;

public class TokenUtil {

    public static int generateToken(){
        Random random = new Random();
        int token = random.nextInt(999999999)+1;
        return token;
    }

}
