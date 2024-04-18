package com.adv2015.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://adventofcode.com/2015/day/7
 * --- Day 7: Some Assembly Required ---
 * --- Part Two ---
 * Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a).
 * What new signal is ultimately provided to wire a?
 *
 * Your puzzle answer was 40149.
 */

public class Part2 {


    private static final List<String> rule_undone = new ArrayList<>();
    private static final Map<String, String> known_vars = new HashMap<>();

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (!processRule(line)) {
                    rule_undone.add(line);
                }
            }

            do {
                Iterator<String> iter = rule_undone.iterator();
                while (iter.hasNext()) {
                    String s = iter.next();
                    if (processRule(s)) {
                        iter.remove();
                    }
                }
            }
            while (!known_vars.containsKey("a"));

            System.out.println("-----------------SOLUTION--------------------------");
            String s = known_vars.get("a");
            System.out.println("a = " + s + ", decimalne je to " + BinaryOperations.contvertToInt(s));
            System.out.println("---------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }

    private static boolean isNumeric(String s) {
        Pattern stringPattern = Pattern.compile("[a-z]");
        Matcher m = stringPattern.matcher(s);
        return !(m.find());
    }

    private static boolean processRule(String rule) {
        String[] parts = rule.split(" -> ");
        if (parts[1].trim().equals("b")) {
            known_vars.put("b", BinaryOperations.convertToBinary("956", 16));
            return true;
        }
        String[] leftParts = parts[0].split(" ");
        int leftPartsLen = leftParts.length;
        switch (leftPartsLen) {
            case 1: {
                // operace prirazeni ma vlevo jen jeden operand
                String left = parts[0].trim();
                String right = parts[1].trim();
                if (!isNumeric(left)) {
                    if (known_vars.containsKey(left)) {
                        known_vars.put(right, known_vars.get(left));
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    String numBin = BinaryOperations.convertToBinary(leftParts[0].trim(), 16);
                    known_vars.put(right, numBin);
                    return true;
                }
            }
            case 2: {
                // negace
                String oper = leftParts[0].trim();
                String left = leftParts[1].trim();
                String right = parts[1].trim();
                if (!oper.equals("NOT")) {
                    System.out.println("----------------- ERROR ------------------------------------------------" + leftParts[0] + "++++");
                }
                if (!isNumeric(left)) {
                    if (known_vars.containsKey(left)) {
                        known_vars.put(right, Operation.not(known_vars.get(left)));
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    System.out.println(" ------------------------------ ERROR --------------------------------------" + rule);
                    return false;
                }
            }
            case 3: {
                // jina operace
                String op1 = leftParts[0];
                String op2 = leftParts[2];
                String operator = leftParts[1];
                String result = "";
                String operand1 = "";
                String operand2 = "";

                if (isNumeric(op1)) {
                    operand1 = BinaryOperations.convertToBinary(op1, 16);
                } else {
                    if (known_vars.containsKey(op1)) {
                        operand1 = known_vars.get(op1);
                    }
                }
                if (isNumeric(op2)) {
                    operand2 = BinaryOperations.convertToBinary(op2, 16);
                } else {
                    if (known_vars.containsKey(op2)) {
                        operand2 = known_vars.get(op2);
                    }
                }
                if (!operand1.isEmpty() && !operand2.isEmpty()) {
                    switch (operator) {
                        case "AND":
                            result = Operation.and(operand1, operand2);
                            break;
                        case "OR":
                            result = Operation.or(operand1, operand2);
                            break;
                        case "LSHIFT":
                            result = Operation.lshift(operand1, Integer.parseInt(op2));
                            break;
                        case "RSHIFT":
                            result = Operation.rshift(operand1, Integer.parseInt(op2));
                            break;
                    }
                    known_vars.put(parts[1].trim(), result);
                    return true;
                } else {
                    return false;
                }
            }
            default:
                System.out.println("jina operace");
        }
        return false;
    }
}
