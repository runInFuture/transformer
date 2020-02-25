package com.wuyi.transformer;

import java.util.Random;

public class Util {
    public static String randomToken() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String source = "abcdefghijklmnopqrstuvwxyz";
        for (int index = 0; index < 10; index++) {
            builder.append(source.charAt(random.nextInt(source.length())));
        }
        return builder.toString();
    }
}
