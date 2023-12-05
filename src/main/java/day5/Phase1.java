package main.java.day5;


import main.java.day5.domain.Almanac;

import static main.java.day5.LocationCalculator.calculateLocation;

public class Phase1 {
    protected static long findLowestLocation(Almanac almanac) {
        long lowestLocation = Long.MAX_VALUE;
        for (long seed : almanac.getSeeds()) {
            long location = calculateLocation(seed, almanac);
            if (location < lowestLocation) {
                lowestLocation = location;
            }
        }
        return lowestLocation;
    }
}
