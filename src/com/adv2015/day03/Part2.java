package com.adv2015.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * --- Part Two ---
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
 *
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
 *
 * This year, how many houses receive at least one present?
 *
 * For example:
 *
 * ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 * ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 * ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 *
 * Your puzzle answer was 2631.
 */

public class Part2 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            int santaX = 0;
            int santaY = 0;
            int roboX = 0;
            int roboY = 0;
            Set<House> houses = new HashSet<>();
            houses.add(new House(0, 0));
            int count = 0;

            for (int i = 0; i < line.length(); i++) {
                count++;
                char ch = line.charAt(i);
                switch (ch) {
                    case '<':
                        if ((count % 2) == 0) {
                            roboX--;
                        } else {
                            santaX--;
                        }
                        break;
                    case '>':
                        if ((count % 2) == 0) {
                            roboX++;
                        } else {
                            santaX++;
                        }
                        break;
                    case '^':
                        if ((count % 2) == 0) {
                            roboY++;
                        } else {
                            santaY++;
                        }
                        break;
                    case 'v':
                        if ((count % 2) == 0) {
                            roboY--;
                        } else {
                            santaY--;
                    }

                        break;
                }
                if ((count % 2) == 0) {
                    houses.add(new House(roboX, roboY));
                } else {
                    houses.add(new House(santaX, santaY));
                }
            }

            System.out.println("-----------------SOLUTION--------------------------");
            System.out.println("houses = " + houses.size());
            System.out.println("---------------------------------------------------");

            br.close();

        } catch (IOException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}
