package com.aoc.day9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncodingError {
    private static List<Long> list;
    private static final int PREAMBLE_LENGTH = 25;
    private static long sum;

    static {
        try (Stream<String> stream = Files.lines(new File(
                EncodingError.class.getClassLoader().getResource("day-9.txt").getFile()).toPath())) {
            list = stream
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 9: Part One ---------");
        for (int i = PREAMBLE_LENGTH; i < list.size(); i++) {
            long answer = list.get(i);
            Set<Long> temp = new HashSet<>();
            int startIndex = i - PREAMBLE_LENGTH;
            for (int j = startIndex; j < i; j++) {
                if (temp.contains(list.get(j))) {
                    break;
                }

                temp.add(answer - list.get(j));
                if (j == i-1) {
                    sum = answer;
                    System.out.println("The puzzle answer is " + answer + "\n");
                    return;
                }
            }
        }
    }

    public static void partTwo() {
        System.out.println("--------- Day 9: Part Two ---------");
        long tempSum = 0;
        List<Long> num = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            num.add(list.get(i));
            tempSum += list.get(i);

            while (tempSum > sum) {
                tempSum -= num.get(0);
                num.remove(0);
            }

            if (tempSum == sum) {
                long min = num.stream().min(Long::compare).get();
                long max = num.stream().max(Long::compare).get();
                long answer = min + max;
                System.out.println("The puzzle answer is " + answer + "\n");
                return;
            }
        }
    }
}
