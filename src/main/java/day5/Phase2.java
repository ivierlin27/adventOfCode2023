package main.java.day5;

import main.java.day5.domain.Almanac;

import java.util.List;

import static main.java.day5.LocationCalculator.calculateLocation;

public class Phase2 {
    public static long findLowestLocation(Almanac almanac) {
        long lowestLocation = Long.MAX_VALUE;
        List<Long> seeds = almanac.getSeeds();
        for (int x = 0; x < seeds.size(); x+= 2) {
            long rangeStart = seeds.get(x);
            long rangeEnd = rangeStart + seeds.get(x + 1);
            for (long y = rangeStart; y < rangeEnd; y++) {
                long location = calculateLocation(y, almanac);
                if (location < lowestLocation) {
                    lowestLocation = location;
                }
            }
        }
        return lowestLocation;
    }
}
