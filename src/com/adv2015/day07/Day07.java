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
 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic gates! Unfortunately,
 * little Bobby is a little under the recommended age range, and he needs help assembling the circuit.
 *
 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit signal (a number from 0 to 65535).
 * A signal is provided to each wire by a gate, another wire, or some specific value.
 * Each wire can only get a signal from one source, but can provide its signal to multiple destinations.
 * A gate provides no signal until all of its inputs have a signal.
 *
 * The included instructions booklet describes how to connect the parts together: x AND y -> z means
 * to connect wires x and y to an AND gate, and then connect its output to wire z.
 *
 * For example:
 *
 * 123 -> x means that the signal 123 is provided to wire x.
 * x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
 * p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
 * NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
 * Other possible gates include OR (bitwise OR) and RSHIFT (right-shift). If, for some reason, you'd like
 * to emulate the circuit instead, almost all programming languages (for example, C, JavaScript, or Python)
 * provide operators for these gates.
 *
 * For example, here is a simple circuit:
 *
 * 123 -> x
 * 456 -> y
 * x AND y -> d
 * x OR y -> e
 * x LSHIFT 2 -> f
 * y RSHIFT 2 -> g
 * NOT x -> h
 * NOT y -> i
 * After it is run, these are the signals on the wires:
 *
 * d: 72
 * e: 507
 * f: 492
 * g: 114
 * h: 65412
 * i: 65079
 * x: 123
 * y: 456
 * In little Bobby's kit's instructions booklet (provided as your puzzle input),
 * what signal is ultimately provided to wire a?
 *
 * Your puzzle answer was 956.
 */

public class Day07 {

    private static final List<String> rule_undone = new ArrayList<>();
    private static final Map<String, String> known_vars = new HashMap<>();

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (!processRule(line)){
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
        }
        return false;
    }
}