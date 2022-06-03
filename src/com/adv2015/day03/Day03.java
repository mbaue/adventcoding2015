package com.adv2015.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * https://adventofcode.com/2015/day/3
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 *
 * He begins by delivering a present to the house at his starting location,
 * and then an elf at the North Pole calls him via radio and tells him where to move next.
 * Moves are always exactly one house to the north (^), south (v), east (>), or west (<).
 * After each move, he delivers another present to the house at his new location.
 *
 * However, the elf back at the north pole has had a little too much eggnog, and so his
 * directions are a little off, and Santa ends up visiting some houses more than once.
 * How many houses receive at least one present?
 *
 * For example:
 *
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 *
 * Your puzzle answer was 2572.
 */

public class Day03 {
    public static void main(String[] args) {
        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            int x = 0;
            int y = 0;
            Set<House> houses = new HashSet<>();
            houses.add(new House(0,0));

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                switch(ch) {
                    case '<':
                        x--;
                        break;
                    case '>':
                        x++;
                        break;
                    case '^':
                        y++;
                        break;
                    case 'v':
                        y--;
                        break;
                }
                houses.add(new House(x, y));
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
