package com.adv2015.day07;

public class BinaryOperations {
    public static String convertToBinary(String num, int positionsNum) {
        String s = Integer.toBinaryString(Integer.parseInt(num));
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, (positionsNum - l))));
        sb.append(s);
        return sb.toString();
    }

    public static int contvertToInt(String s) {
        int l = s.length();
        int retNum = 0;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '1') {
                retNum = (retNum << 1) + 1;
            } else {
                retNum = retNum << 1;
            }
        }
        return retNum;
    }
}
