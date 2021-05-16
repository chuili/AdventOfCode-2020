package com.aoc.day8;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandheldHalting {
    private static List<String> list;

    static {
        try (Stream<String> stream = Files.lines(new File(
                HandheldHalting.class.getClassLoader().getResource("day-8.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AllArgsConstructor
    static class Result {
        boolean isLoop;
        int accumulator;
    }

    public static void partOne() {
        System.out.println("--------- Day 8: Part One ---------");
        Result result = validateCmd(list);
        System.out.println("The puzzle answer is " + result.accumulator + "\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 8: Part One ---------");
        Result result;
        for (int i = 0; i < list.size(); i++) {
            List<String> modified = list.stream().collect(Collectors.toList());
            String[] commands = list.get(i).trim().split(" ");
            String cmd = commands[0].trim();
            if ("jmp".equalsIgnoreCase(cmd) || "nop".equalsIgnoreCase(cmd)) {
                String newCmd = "jmp".equalsIgnoreCase(cmd) ? "nop" : "jmp";
                modified.remove(i);
                modified.add(i, newCmd + " " + commands[1]);
                result = validateCmd(modified);
                if (!result.isLoop) {
                    System.out.println("The puzzle answer is " + result.accumulator +"\n");
                    return;
                }
            }
        }
    }

    private static Result validateCmd(final List<String> modified) {
        Set<Integer> record = new HashSet<>();
        int accumulator = 0;
        int index = 0;
        while (index < modified.size()) {
            if (record.contains(index)) {
                return new Result(true, accumulator);
            }
            record.add(index);
            String[] command = modified.get(index).trim().split(" ");
            switch (command[0].trim()) {
                case "acc": {
                    accumulator += Integer.parseInt(command[1].trim());
                    index++;
                    break;
                }
                case "jmp": {
                    index += Integer.parseInt(command[1].trim());
                    break;
                }
                case "nop": {
                    index++;
                    break;
                }
            }
        }
        return new Result(false, accumulator);
    }
}
