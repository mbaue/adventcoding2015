package com.adv2015.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2015/day/8
 * --- Day 8: Matchsticks ---
 * Space on the sleigh is limited this year, and so Santa will be bringing his list as a digital copy.
 * He needs to know how much space it will take up when stored.
 *
 * It is common in many programming languages to provide a way to escape special characters in strings.
 * For example, C, JavaScript, Perl, Python, and even PHP handle special characters in very similar ways.
 *
 * However, it is important to realize the difference between the number of characters in the code
 * representation of the string literal and the number of characters in the in-memory string itself.
 *
 * For example:
 *
 * "" is 2 characters of code (the two double quotes), but the string contains zero characters.
 * "abc" is 5 characters of code, but 3 characters in the string data.
 * "aaa\"aaa" is 10 characters of code, but the string itself contains six "a" characters and a single,
 * escaped quote character, for a total of 7 characters in the string data.
 * "\x27" is 6 characters of code, but the string itself contains just one - an apostrophe ('),
 * escaped using hexadecimal notation.
 * Santa's list is a file that contains many double-quoted string literals, one on each line.
 * The only escape sequences used are \\ (which represents a single backslash), \" (which represents
 * a lone double-quote character), and \x plus two hexadecimal characters (which represents a single
 * character with that ASCII code).
 *
 * Disregarding the whitespace in the file, what is the number of characters of code for string
 * literals minus the number of characters in memory for the values of the strings in total for the entire file?
 *
 * For example, given the four strings above, the total number of characters of string code (2 + 5 + 10 + 6 = 23)
 * minus the total number of characters in memory for string values (0 + 3 + 7 + 1 = 11) is 23 - 11 = 12.
 *
 * Your puzzle answer was 1333.
 */

public class Day08 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int charsMemoryTotal = 0;
            int charsCntTotal = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                int lineLength = line.length();
                int charsCnt = 0;
                int i = 0;
                while (i < lineLength) {
                    if (line.charAt(i) == '\\') {
                        i++;
                        if (line.charAt(i) == 'x') {
                            i = i+3;
                            charsCnt++;
                        } else if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                            i++;
                            charsCnt++;
                        } else {
                            System.out.println(" ++++++++++++++++++ tohle by nemelo nastat nikdy +++++++++++++++");
                        }
                    } else {
                        i++;
                        charsCnt++;
                    }

                }
                charsCnt-=2;
                System.out.println("       linelength " + lineLength);
                System.out.println("       charscnt " + charsCnt);
                charsMemoryTotal += lineLength;
                charsCntTotal += charsCnt;
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("mems " +charsMemoryTotal);
            System.out.println("char " + charsCntTotal);
            System.out.println("solution = " + (charsMemoryTotal - charsCntTotal));

            System.out.println("---------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}
