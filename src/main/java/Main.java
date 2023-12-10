package main.java;

import main.java.day8.Day8;

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
        duration("Day 8, phase 1: ", Day8::phase1);
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
