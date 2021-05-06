package com.aoc.day4;

import com.aoc.day1.ReportRepair;

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
                ReportRepair.class.getClassLoader().getResource("day-4.txt").getFile()).toPath())) {
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
                            if (requiredFields.containsKey(b[0])) {
                                requiredFields.put(b[0].trim(), 1);
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
        reset();
        for (String line : list) {
            if (line.trim().length() > 0) {
                String[] data = line.trim().split(" ");
                Arrays.stream(data)
                        .map(a -> a.trim().split(":"))
                        .forEach(b -> {
                            String key = b[0].trim();
                            String value = b[1].trim();
                            switch (key) {
                                case "byr": {
                                    // 1920-2002
                                    int byr = Integer.parseInt(value);
                                    if (byr >= 1920 && byr <= 2002) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "iyr": {
                                    // 2010-2020
                                    int iyr = Integer.parseInt(value);
                                    if (iyr >= 2010 && iyr <= 2020) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "eyr": {
                                    // 2020-2030
                                    int eyr = Integer.parseInt(value);
                                    if (eyr >= 2020 && eyr <= 2030) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "hgt": {
                                    // 150-193cm; 59-76in
                                    if (value.matches("^(1[5-8][0-9]|19[0-3])cm$|^(59|6[0-9]|7[0-6])in$")) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "hcl": {
                                    // #{6 characters 0-9 or a-f
                                    if (value.matches("^#[0-9a-f]{6}$")) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "ecl": {
                                    // amb|blu|brn|gry|grn|hzl|oth
                                    String[] eyeColor = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                                    if (Arrays.stream(eyeColor).anyMatch(a -> a.equals(value))) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                case "pid": {
                                    // 9 digits number, including leading 0
                                    if (value.matches("^[0-9]{9}$")) {
                                        requiredFields.put(key, 1);
                                    }
                                    break;
                                }
                                default:
                                    break;
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
}
