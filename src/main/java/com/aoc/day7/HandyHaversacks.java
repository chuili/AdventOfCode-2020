package com.aoc.day7;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandyHaversacks {
    private static List<String> list;
    private static final String INITIAL_BAG_COLOR = "shiny gold";

    static {
        try (Stream<String> stream = Files.lines(new File(
                HandyHaversacks.class.getClassLoader().getResource("day-7.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 7: Part One ---------");
        AtomicInteger result = new AtomicInteger();
        Set<String> resultColor = new HashSet<>();
        Queue<String> colors = new PriorityQueue<>();
        colors.add(INITIAL_BAG_COLOR);
        while (!colors.isEmpty()) {
            String color = colors.poll();
            list.stream().filter(a -> a.contains(color)).forEach(b -> {
                String temp = b.split("bags contain")[0].trim();
                if (!color.equalsIgnoreCase(temp) && !resultColor.contains(temp)) {
                    colors.add(temp);
                    resultColor.add(temp);
                    result.getAndIncrement();
                }
            });
        }
        System.out.println("The puzzle answer is " + result.get() +"\n");
    }

    @AllArgsConstructor
    static class Bag {
        String color;
        int number;
    }

    public static void partTwo() {
        System.out.println("--------- Day 7: Part Two ---------");
        Map<String, String> colorBagMap = list.stream()
                .map(a -> a.trim().split("bags contain"))
                .collect(Collectors.toMap(b -> b[0].trim(), b -> b[1].trim()));
        List<Bag> colors = new ArrayList<>();
        colors.add(new Bag(INITIAL_BAG_COLOR, 1));
        int result = 0;
        int index = 0;
        while (index < colors.size()) {
            String color = colors.get(index).color;
            int num = colors.get(index).number;
            if (colorBagMap.containsKey(color)) {
                String[] bags = colorBagMap.get(color).split(",");
                for (String bag : bags) {
                    if (!bag.contains("no other")) {
                        String[] temp = bag.trim().split(" ");
                        String tempColor = temp[1].trim() + " " + temp[2].trim();
                        int tempNum = Integer.parseInt(temp[0].trim());
                        colors.add(new Bag(tempColor, num * tempNum));
                        result += num * tempNum;
                    }
                }
            }
            index++;
        }

        System.out.println("The puzzle answer is " + result + "\n");
    }
}
