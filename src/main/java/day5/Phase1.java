package main.java.day5;


import main.java.day5.domain.Almanac;
import main.java.day5.domain.RangeMapEntry;

import java.util.List;

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

    private static long calculateLocation(long seed, Almanac almanac) {
        long soil = findValueInMap(seed, almanac.getSeedToSoilMap());
        long fertilizer = findValueInMap(soil, almanac.getSoilToFertilizerMap());
        long water = findValueInMap(fertilizer, almanac.getFertilizerToWaterMap());
        long light = findValueInMap(water, almanac.getWaterToLightMap());
        long temperature = findValueInMap(light, almanac.getLightToTemperatureMap());
        long humidity = findValueInMap(temperature, almanac.getTemperatureToHumidityMap());
        return findValueInMap(humidity, almanac.getHumidityToLocationMap());
    }

    private static long findValueInMap(long seed, List<RangeMapEntry> rangeMap) {
        return rangeMap.stream()
                .filter(rangeMapEntry -> seedInRange(seed, rangeMapEntry.getSource(), rangeMapEntry.getRangeLength()))
                .findFirst()
                .map(rangeMapEntry -> rangeMapEntry.getDestination() + getPointInRange(seed, rangeMapEntry.getSource()))
                .orElse(seed);
    }

    private static boolean seedInRange(long seed, long rangeStart, long rangeLength) {
        return rangeStart <= seed && seed < (rangeStart + rangeLength);
    }

    private static long getPointInRange(long seed, long rangeStart) {
        return seed - rangeStart;
    }
}
