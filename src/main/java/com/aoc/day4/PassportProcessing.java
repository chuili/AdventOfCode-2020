package com.aoc.day4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportProcessing {
    private static List<String> list;
    private static Map<String, Integer> requiredFields = new HashMap<>();

    static {
        try (Stream<String> stream = Files.lines(new File(
                PassportProcessing.class.getClassLoader().getResource("day-4.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 4: Part One ---------");
        int result = 0;
        reset();
        for (String line : list) {
            if (line.trim().length() > 0) {
                String[] data = line.trim().split(" ");
                Arrays.stream(data)
                        .map(a -> a.trim().split(":"))
                        .forEach(b -> {
                            String key = b[0].trim();
                            if (requiredFields.containsKey(key)) {
                                requiredFields.put(key, 1);
                            }
                        });
                continue;
            }
            if (requiredFields.entrySet().stream().filter(a -> a.getValue() == 0).count() == 0) {
                result++;
            }
            reset();
        }
        System.out.println("The puzzle answer is " + result +"\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 4: Part Two ---------");
        int result = 0;
        Map<String, String> regex = constructRegexMap();
        reset();
        for (String line : list) {
            if (line.trim().length() > 0) {
                String[] data = line.trim().split(" ");
                Arrays.stream(data)
                        .map(a -> a.trim().split(":"))
                        .forEach(b -> {
                            String key = b[0].trim();
                            String value = b[1].trim();
                            if (regex.containsKey(key) && value.matches(regex.get(key))) {
                                requiredFields.put(key, 1);
                            }
                        });
                continue;
            }
            if (requiredFields.entrySet().stream().filter(a -> a.getValue() == 0).count() == 0) {
                result++;
            }
            reset();
        }
        System.out.println("The puzzle answer is " + result +"\n");
    }

    private static void reset() {
        requiredFields.put("byr", 0);
        requiredFields.put("iyr", 0);
        requiredFields.put("eyr", 0);
        requiredFields.put("hgt", 0);
        requiredFields.put("hcl", 0);
        requiredFields.put("ecl", 0);
        requiredFields.put("pid", 0);
    }

    private static Map<String, String> constructRegexMap() {
        Map<String, String> regex = new HashMap<>();
        regex.put("byr", "^(19[2-9][0-9]|200[0-2])$"); // 1920-2002
        regex.put("iyr", "^(201[0-9]|2020)$"); // 2010-2020
        regex.put("eyr", "^(202[0-9]|2030)$"); // 2020-2030
        regex.put("hgt", "^(1[5-8][0-9]|19[0-3])cm$|^(59|6[0-9]|7[0-6])in$"); // 150-193cm; 59-76in
        regex.put("hcl", "^#[0-9a-f]{6}$"); // #{6 characters 0-9 or a-f
        regex.put("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$"); // amb|blu|brn|gry|grn|hzl|oth
        regex.put("pid", "^[0-9]{9}$"); // 9 digits number, including leading 0
        return  regex;
    }
}
