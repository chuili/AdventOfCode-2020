package com.aoc.day5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryBoarding {
    private static List<String> list;
    private static int minRow, maxRow;
    private static int minColumn, maxColumn;
    private static List<Integer> seatList = new ArrayList<>();

    static {
        try (Stream<String> stream = Files.lines(new File(
                BinaryBoarding.class.getClassLoader().getResource("day-5.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 5: Part One ---------");
        reset();
        int result = 0;
        for (String line : list) {
            byte[] hint = line.getBytes();
            for (byte character : hint) {
                switch (character) {
                    case 'F': {
                        maxRow = minRow + (maxRow - minRow)/2;
                        break;
                    }
                    case 'B': {
                        minRow = maxRow - (maxRow - minRow)/2;
                        break;
                    }
                    case 'L': {
                        maxColumn = minColumn + (maxColumn - minColumn)/2;
                        break;
                    }
                    case 'R': {
                        minColumn = maxColumn - (maxColumn - minColumn)/2;
                    }
                }
            }

            int seatId = minRow * 8 + minColumn;
            seatList.add(seatId);
            reset();
        }
        result = seatList.stream().max(Integer::compare).get();
        System.out.println("The puzzle answer is " + result +"\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 5: Part Two ---------");
        List<Integer> sortedList = seatList.stream().sorted().collect(Collectors.toList());
        for (int i=0; i < (sortedList.size() - 1); i++) {
            if ((sortedList.get(i+1) - sortedList.get(i)) > 1) {
                System.out.println("The puzzle answer is " + (sortedList.get(i) + 1));
                return;
            }
        }
    }

    private static void reset() {
        minRow = 0;
        maxRow = 127;
        minColumn = 0;
        maxColumn = 7;
    }
}
