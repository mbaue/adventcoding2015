package com.adv2015.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * https://adventofcode.com/2015/day/5
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 *
 * A nice string is one with all of the following properties:
 *
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 * For example:
 *
 * ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
 * aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter.
 * haegwjzuvuyypxyu is naughty because it contains the string xy.
 * dvszwmarrgswjxmb is naughty because it contains only one vowel.
 * How many strings are nice?
 *
 * Your puzzle answer was 258.
 */

public class Day05 {

    private static final Set<String> nepovoleneDvojice = new HashSet<>(Arrays.asList("ab", "cd", "pq", "xy"));
    private static final String samohl = "aeiou";

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int niceStringCnt = 0;

            while ((line = br.readLine()) != null) {
                boolean doubledChar = false;
                int vowelCnt = 0;
                boolean forbiddenChar = false;

                for (int i = 0; i < line.length() - 1; i++) {
                    String s = line.substring(i, i + 2);
                    if (nepovoleneDvojice.contains(s)) {
                        forbiddenChar = true;
                        break;
                    } else {
                        if (s.charAt(0) == s.charAt(1)) {
                            doubledChar = true;
                        }
                        if (samohl.indexOf(s.charAt(1)) >= 0) {
                            vowelCnt++;
                        }
                    }
                }
                if (samohl.indexOf(line.charAt(0)) >= 0) {
                    vowelCnt++;
                }
                if (vowelCnt >= 3 && doubledChar && !forbiddenChar) {
                    niceStringCnt++;
                }
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("nice strings count = " + niceStringCnt);
            System.out.println("---------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}
