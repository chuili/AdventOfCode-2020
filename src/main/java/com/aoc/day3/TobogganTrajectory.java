package com.aoc.day3;

import com.aoc.day1.ReportRepair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TobogganTrajectory {
    private static List<String> list;

    static {
        try (Stream<String> stream = Files.lines(new File(
                ReportRepair.class.getClassLoader().getResource("day-3.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 3: Part One ---------");
        int result = traverse(3, 1);
        System.out.println("The puzzle answer is " + result + "\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 3: Part Two ---------");
        List<Integer> result = new ArrayList<>();
        // Right 1, down 1 - 55
        result.add(traverse(1, 1));
        // Right 3, down 1 - 250
        result.add(traverse(3, 1));
        // Right 5, down 1 - 54
        result.add(traverse(5, 1));
        // Right 7, down 1 - 55
        result.add(traverse(7, 1));
        // Right 1, down 2 - 39
        result.add(traverse(1, 2));

//        result.forEach(a -> System.out.println(a));
        long answer = result.stream().reduce(1, (a, b) -> a * b);
        System.out.println("The puzzle answer is " + answer + "\n");
    }

    private static int traverse(final int right, final int down) {
        int counter = 0;
        int index = 0;
        for (int i = down; i < list.size(); i += down) {
            String line = list.get(i);
            index += right;
            if (index >= line.length()) {
                index = index % line.length();
            }
            if (line.charAt(index) == '#') {
                counter++;
            }
        }
        return counter;
    }
}