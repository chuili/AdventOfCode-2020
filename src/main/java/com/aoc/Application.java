package com.aoc;

import com.aoc.day1.ReportRepair;
import com.aoc.day2.PasswordPhilosophy;
import com.aoc.day3.TobogganTrajectory;
import com.aoc.day4.PassportProcessing;
import com.aoc.day5.BinaryBoarding;
import com.aoc.day6.CustomCustoms;
import com.aoc.day7.HandyHaversacks;
import com.aoc.day8.HandheldHalting;
import com.aoc.day9.EncodingError;

public class Application {

    public static void main(String[] args) {
        // Day 1
        ReportRepair.partOne();
        ReportRepair.partTwo();

        // Day 2
        PasswordPhilosophy.partOne();
        PasswordPhilosophy.partTwo();

        // Day 3
        TobogganTrajectory.partOne();
        TobogganTrajectory.partTwo();

        // Day 4
        PassportProcessing.partOne();
        PassportProcessing.partTwo();

        // Day 5
        BinaryBoarding.partOne();
        BinaryBoarding.partTwo();

        // Day 6
        CustomCustoms.partOne();
        CustomCustoms.partTwo();

        // Day 7
        HandyHaversacks.partOne();
        HandyHaversacks.partTwo();

        // Day 8
        HandheldHalting.partOne();
        HandheldHalting.partTwo();

        // Day 9
        EncodingError.partOne();
        EncodingError.partTwo();
    }
}
