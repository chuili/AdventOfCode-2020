package com.aoc.day6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {
    private static List<String> list;
    private static final int MAX_QUESTION = 26;

    static {
        try (Stream<String> stream = Files.lines(new File(
                CustomCustoms.class.getClassLoader().getResource("day-6.txt").getFile()).toPath())) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        System.out.println("--------- Day 6: Part One ---------");
        int result = 0;
        StringBuffer answer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i).trim();
            if (line.length() > 0) {
                answer.append(line);
                if (i != list.size() - 1) {
                    continue;
                }
            }

            result += answer.chars().distinct().count();
            answer.delete(0, answer.length());
        }
        System.out.println("The puzzle answer is " + result +"\n");
    }

    public static void partTwo() {
        System.out.println("--------- Day 6: Part Two ---------");
        int result = 0;
        Boolean[] groupYes = new Boolean[MAX_QUESTION];
        Arrays.fill(groupYes, true);
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i).trim();
            if (line.length() > 0) {
                Boolean[] singleYes = new Boolean[MAX_QUESTION];
                Arrays.fill(singleYes, false);
                for (int j = 0; j < line.length(); j++) {
                    if (groupYes[line.charAt(j) - 'a']) {
                        singleYes[line.charAt(j) - 'a'] = true;
                    }
                }
                System.arraycopy(singleYes, 0, groupYes, 0, MAX_QUESTION);
                if (i != list.size() - 1) {
                    continue;
                }
            }

            result += Arrays.stream(groupYes).filter(a -> a == true).count();
            Arrays.fill(groupYes, true);
        }
        System.out.println("The puzzle answer is " + result +"\n");
    }
}
