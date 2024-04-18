package com.adv2015.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2015/day/6
 * --- Day 6: Probably a Fire Hazard ---
 * --- Part Two ---
 * You just finish implementing your winning light pattern when you realize you mistranslated
 * Santa's message from Ancient Nordic Elvish.
 * The light grid you bought actually has individual brightness controls;
 * each light can have a brightness of zero or more. The lights all start at zero.
 *
 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
 * What is the total brightness of all lights combined after following Santa's instructions?
 *
 * For example:
 * turn on 0,0 through 0,0 would increase the total brightness by 1.
 * toggle 0,0 through 999,999 would increase the total brightness by 2000000.
 *
 * Your puzzle answer was 17836115.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int[][] light = new int[1000][1000];
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    light[i][j] = 0;
                }
            }

            while ((line = br.readLine()) != null) {
                line = line.replace("through ", "").replace("turn on", "turnon").replace("turn off", "turnoff");
                String[] parts = line.split(" ");
                String[] lefttop = parts[1].split(",");
                String[] bottomright = parts[2].split(",");
                String first = parts[0];
                if (first.equals("turnon")) {
                    for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                        for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                            light[i][j] = light[i][j] + 1;
                        }
                    }
                } else if (first.equals("turnoff")) {
                    for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                        for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                            if (light[i][j] > 0) {
                                light[i][j] = light[i][j] - 1;
                            }
                        }
                    }
                } else if (first.equals("toggle")) {
                    for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                        for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                            light[i][j] = light[i][j] + 2;
                        }
                    }
                }
            }
            int solution = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    solution += light[i][j];
                }
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("number of lights on = " + solution);
            System.out.println("---------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}
