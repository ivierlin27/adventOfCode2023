package main.java;

import main.java.day1.Day1;
import main.java.day2.Day2;
import main.java.day3.Day3;
import main.java.day4.Day4;
import main.java.day5.Day5;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        duration("Day 1, phase 1: ", Day1::phase1);
        duration("Day 1, phase 2: ", Day1::phase2);
        duration("Day 2, phase 1: ", Day2::phase1);
        duration("Day 2, phase 2: ", Day2::phase2);
        duration("Day 3, phase 1: ", Day3::phase1);
        duration("Day 3, phase 2: ", Day3::phase2);
        duration("Day 4, phase 1: ", Day4::phase1);
        duration("Day 4, phase 2: ", Day4::phase2);
        duration("Day 5, phase 1: ", Day5::phase1);
//        duration("Day 5, phase 2: ", Day5::phase2); // too slow!
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
