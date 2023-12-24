package main.java;

import main.java.day22.Day22;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
//        duration("Day 1, phase 1: ", Day1::phase1);
//        duration("Day 1, phase 2: ", Day1::phase2);
//        duration("Day 2, phase 1: ", Day2::phase1);
//        duration("Day 2, phase 2: ", Day2::phase2);
//        duration("Day 3, phase 1: ", Day3::phase1);
//        duration("Day 3, phase 2: ", Day3::phase2);
//        duration("Day 4, phase 1: ", Day4::phase1);
//        duration("Day 4, phase 2: ", Day4::phase2);
//        duration("Day 5, phase 1: ", Day5::phase1);
//        duration("Day 5, phase 2: ", Day5::phase2); // too slow!
//        duration("Day 6, phase 1: ", Day6::phase1);
//        duration("Day 6, phase 2: ", Day6::phase2);
//        duration("Day 7, phase 1: ", Day7::phase1);
//        duration("Day 7, phase 2: ", Day7::phase2);
//        duration("Day 8, phase 1: ", Day8::phase1);
//        duration("Day 8, phase 2: ", Day8::phase2);
//        duration("Day 9, phase 1: ", Day9::phase1);
//        duration("Day 9, phase 2: ", Day9::phase2);
//        duration("Day 10, phase 1: ", Day10::phase1);
//        duration("Day 10, phase 2: ", Day10::phase2);
//        duration("Day 11, phase 1: ", Day11::phase1);
//        duration("Day 11, phase 2: ", Day11::phase2);
//        duration("Day 12, phase 1: ", Day12::phase1);
//        duration("Day 12, phase 2: ", Day12::phase2);
//        duration("Day 13, phase 1: ", Day13::phase1);
//        duration("Day 13, phase 2: ", Day13::phase2);
//        duration("Day 14, phase 1: ", Day14::phase1);
//        duration("Day 14, phase 2: ", Day14::phase2);
//        duration("Day 15, phase 1: ", Day15::phase1);
//        duration("Day 15, phase 2: ", Day15::phase2);
//        duration("Day 16, phase 1: ", Day16::phase1);
//        duration("Day 16, phase 2: ", Day16::phase2);
//        duration("Day 17, phase 1: ", Day17::phase1);
//        duration("Day 17, phase 2: ", Day17::phase2);
//        duration("Day 18, phase 1: ", Day18::phase1);
//        duration("Day 18, phase 2: ", Day18::phase2);
//        duration("Day 19, phase 1: ", Day19::phase1);
//        duration("Day 19, phase 2: ", Day19::phase2);
//        duration("Day 20, phase 1: ", Day20::phase1);
//        duration("Day 20, phase 2: ", Day20::phase2);
//        duration("Day 21, phase 1: ", Day21::phase1);
//        duration("Day 21, phase 2: ", Day21::phase2);
        duration("Day 22, phase 1: ", Day22::phase1);
    }

    private static <T> void duration(String message, Supplier<? extends T> function) {
        Instant start = Instant.now();
        T result = function.get();
        Instant end = Instant.now();
        System.out.println(message + result + " duration: " + prettyDuration(Duration.between(start, end)));
    }

    private static String prettyDuration(Duration duration) {
        return String.format("%s d %sh %sm %ss",
                duration.toDaysPart(),
                duration.toHoursPart(),
                duration.toMinutesPart(),
                duration.toSecondsPart());
    }
}
