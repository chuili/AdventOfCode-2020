package com.aoc.day2;

import com.aoc.day1.ReportRepair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordPhilosophy {
    private static List<String> list;

    static {
        try (Stream<String> stream = Files.lines(new File(
                ReportRepair.class.getClassLoader().getResource("day-2.txt").getFile()).toPath())) {
            list = stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 2: Part One ---------");
        int result = 0;
        for (String line : list) {
            String[] password = line.split(":");
            String[] policy = password[0].trim().split(" ");
            int counter = (int) password[1].trim().chars()
                    .filter(value -> value == policy[1].trim().charAt(0)).count();
            String[] minMax = policy[0].trim().split("-");
            if (counter >= Integer.parseInt(minMax[0].trim())
                    && counter <= Integer.parseInt(minMax[1].trim())) {
                result++;
            }
        }
        System.out.println("The puzzle answer is " + result);
    }

    public static void partTwo() {
        System.out.println("--------- Day 2: Part Two ---------");
        int result = 0;
        for (String line : list) {
            String[] password = line.split(":");
            String[] policy = password[0].trim().split(" ");
            String[] index = policy[0].trim().split("-");
            char policyAlphabet = policy[1].trim().charAt(0);
            if (password[1].trim().charAt(Integer.parseInt(index[0].trim()) - 1) == policyAlphabet
                    ^ password[1].trim().charAt(Integer.parseInt(index[1].trim()) - 1) == policyAlphabet) {
                result++;
            }
        }
        System.out.println("The puzzle answer is " + result);
    }
}
