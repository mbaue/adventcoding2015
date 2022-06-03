package com.adv2015.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * --- Part Two ---
 * Realizing the error of his ways, Santa has switched to a better model of
 * determining whether a string is naughty or nice. None of the old rules apply,
 * as they are all clearly ridiculous.
 *
 * Now, a nice string is one with all of the following properties:
 *
 * It contains a pair of any two letters that appears at least twice in the string
 * without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 * It contains at least one letter which repeats with exactly one letter between them,
 * like xyx, abcdefeghi (efe), or even aaa.
 * For example:
 *
 * qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter t
 * hat repeats with exactly one letter between them (zxz).
 * xxyxx is nice because it has a pair that appears twice and a letter that repeats
 * with one between, even though the letters used by each rule overlap.
 * uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single
 * letter between them.
 * ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo),
 * but no pair that appears twice.
 * How many strings are nice under these new rules?
 *
 * Your puzzle answer was 53.
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int niceStringCnt = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                boolean repeatedGroup = false;
                boolean repeatedLetter = false;

                for (int i = 0; i < line.length() - 1; i++) {
                    String s = line.substring(i, i + 2);

                    // rule 1
                    if ((i - line.indexOf(s) > 1) || (line.lastIndexOf(s) - i > 1)) {
                        repeatedGroup = true;
                    }

                    // rule 2
                    if (i + 2 < line.length()) {
                        if (line.charAt(i) == line.charAt(i + 2)) {
                            repeatedLetter = true;
                        }
                    }
                    if (i - 2 >= 0) {
                        if (line.charAt(i) == line.charAt(i - 2)) {
                            repeatedLetter = true;
                        }
                    }
                }
                if (repeatedGroup && repeatedLetter) {
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
