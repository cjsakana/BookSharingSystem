package kg.us.sakanatang.utils;

import java.util.Random;

public class Util {
    public static String RandomNumberGenerator() {
        String a = "1234567890qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM";
        Random i = new Random();
        StringBuilder code = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            code.append(a.charAt(i.nextInt(62)));
        }
        return code.toString();
    }


}
