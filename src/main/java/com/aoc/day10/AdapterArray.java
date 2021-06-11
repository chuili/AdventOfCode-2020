package com.aoc.day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {
    private static List<Integer> list;
    private static final Map<Integer, Long> indexCombiCount = new HashMap<>();

    static {
        try (Stream<String> stream = Files.lines(new File(
                AdapterArray.class.getClassLoader().getResource("day-10.txt").getFile()).toPath())) {
            list = stream
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            list.add(0);
            Collections.sort(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 10: Part One ---------");
        int result;
        int count1 = 0;
        int count3 = 0;
        for (int i = 1; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i-1);
            if (diff == 1) {
                count1++;
            }
            else if (diff == 3) {
                count3++;
            }
        }
        result = count1 * ++count3;
        System.out.println("The puzzle answer is " + result + "\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 10: Part Two ---------");
        System.out.println("The puzzle answer is " + findCombinationCount(0) + "\n");
    }

    private static long findCombinationCount(final int index) {
        if (index == list.size() - 1) {
            return 1;
        }

        if (indexCombiCount.containsKey(index)) {
            return indexCombiCount.get(index);
        }

        long count = 0;
        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(i) - list.get(index) > 3) {
                break;
            }
            count += findCombinationCount(i);
        }
        indexCombiCount.put(index, count);
        return count;
    }
}
