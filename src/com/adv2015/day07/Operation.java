package com.adv2015.day07;

public class Operation {

    public static String rshift(String num1, int positions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= positions; i++) {
            sb.append("0");
        }
        sb.append(num1.substring(0, num1.length() - positions));
        return sb.toString();
    }

    public static String and(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) == '1' && num2.charAt(i) == '1') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public static String lshift(String num, int positions) {
        StringBuilder sb = new StringBuilder();
        sb.append(num.substring(positions));
        for (int i = 0; i < positions; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static String or(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) == '1') {
                sb.append('1');
            } else {
                sb.append(num2.charAt(i));
            }
        }
        return sb.toString();
    }


    public static String not(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

}