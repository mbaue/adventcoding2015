package com.adv2015.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2015/day/6
 * --- Day 6: Probably a Fire Hazard ---
 * Because your neighbors keep defeating you in the holiday house decorating contest year after year,
 * you've decided to deploy one million lights in a 1000x1000 grid.
 *
 * Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how
 * to display the ideal lighting configuration.
 *
 * Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are
 * at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off,
 * or toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents
 * opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2
 * therefore refers to 9 lights in a 3x3 square. The lights all start turned off.
 *
 * To defeat your neighbors this year, all you have to do is set up your lights by
 * doing the instructions Santa sent you in order.
 *
 * For example:
 * turn on 0,0 through 999,999 would turn on (or leave on) every light.
 * toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the
 * ones that were on, and turning on the ones that were off.
 * turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
 * After following the instructions, how many lights are lit?
 *
 * Your puzzle answer was 569999.
 */

public class Day06 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            boolean[][] light = new boolean[1000][1000];
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    light[i][j] = false;
                }
            }

            while ((line = br.readLine()) != null) {
                line = line.replace("through ", "").replace("turn on", "turnon").replace("turn off", "turnoff");
                String[] parts = line.split(" ");
                String[] lefttop = parts[1].split(",");
                String[] bottomright = parts[2].split(",");
                String first = parts[0];
                switch (first) {
                    case "turnon":
                        for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                            for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                                light[i][j] = true;
                            }
                        }
                        break;
                    case "turnoff":
                        for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                            for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                                light[i][j] = false;
                            }
                        }
                        break;
                    case "toggle":
                        for (int i = Integer.parseInt(lefttop[0]); i <= Integer.parseInt(bottomright[0]); i++) {
                            for (int j = Integer.parseInt(lefttop[1]); j <= Integer.parseInt(bottomright[1]); j++) {
                                light[i][j] = !light[i][j];
                            }
                        }
                        break;
                }
            }
            int solution = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    if (light[i][j]) {
                        solution++;
                    }
                }
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("number of lights on = " + solution);
            System.out.println("---------------------------------------------------");

            br.close();
        } catch (
                IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}