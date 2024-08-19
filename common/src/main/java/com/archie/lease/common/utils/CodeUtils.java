package com.archie.lease.common.utils;

import java.util.Random;

public class CodeUtils {

    public static String getRandomCode(Integer length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            builder.append(num);
        }
        return builder.toString();
    }

}
