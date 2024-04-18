package com.adv2015.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2015/day/8
 * --- Day 8: Matchsticks ---
 * --- Part Two ---
 * Now, let's go the other way. In addition to finding the number of characters of code,
 * you should now encode each code representation as a new string and find the number of
 * characters of the new encoded representation, including the surrounding double quotes.
 *
 * For example:
 *
 * "" encodes to "\"\"", an increase from 2 characters to 6.
 * "abc" encodes to "\"abc\"", an increase from 5 characters to 9.
 * "aaa\"aaa" encodes to "\"aaa\\\"aaa\"", an increase from 10 characters to 16.
 * "\x27" encodes to "\"\\x27\"", an increase from 6 characters to 11.
 * Your task is to find the total number of characters to represent the newly encoded strings minus
 * the number of characters of code in each original string literal. For example, for the strings above,
 * the total encoded length (6 + 9 + 16 + 11 = 42) minus the characters in the original code representation
 * (23, just like in the first part of this puzzle) is 42 - 23 = 19.
 *
 * Your puzzle answer was 2046.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int solution = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line + " .......................................................................");
                int lineLength = line.length();
                int charsMem = lineLength + 2;
                int i = 0;
                while (i < lineLength) {
                    if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                        charsMem++;
                    }
                    i++;
                }

                System.out.println("       linelength " + lineLength);
                System.out.println("       charscnt " + charsMem);
                solution += (charsMem-lineLength);
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("solution = " + solution);
            System.out.println("---------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}
