package com.aoc.day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportRepair {
    private static final int sum = 2020;
    private static List<Integer> list;

    static {
        try (Stream<String> stream = Files.lines(new File(
                ReportRepair.class.getClassLoader().getResource("day-1.txt").getFile()).toPath())) {
            list = stream
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 1: Part One ---------");
        Map<Integer, Integer> map = new HashMap<>();
        Iterator<Integer> iterator = list.iterator();
        int curr;
        while (iterator.hasNext()) {
            curr = iterator.next();
            if (map.containsKey(curr)) {
                System.out.println("The two entries are " + curr + " and " + map.get(curr));
                System.out.println("The puzzle answer is " + (curr * map.get(curr)) + "\n");
                return;
            }

            map.put(sum - curr, curr);
        }
    }

    public static void partTwo() {
        System.out.println("--------- Day 1: Part Two ---------");
        Map<Integer, Integer[]> map = new HashMap<>();
        int curr;
        for (int i = 0; i < list.size(); i++) {
            curr = list.get(i);
            if (map.containsKey(curr)) {
                System.out.println("The three entries are " + curr + ", " + map.get(curr)[0] + " and " + map.get(curr)[1]);
                System.out.println("The puzzle answer is " + (curr * map.get(curr)[0] * map.get(curr)[1]) + "\n");
                return;
            }

            for (int j = 0; j < list.size(); j++) {
                if (j == i) {
                    continue;
                }
                map.put(sum - list.get(i) - list.get(j), new Integer[]{list.get(i), list.get(j)});
            }
        }
    }
}
