package main.java.day5;

import main.java.day5.domain.Almanac;
import main.java.day5.domain.RangeMapEntry;

import java.util.List;

public class LocationCalculator {
    protected static long calculateLocation(long seed, Almanac almanac) {
        long soil = findValueInMap(seed, almanac.getSeedToSoilMap());
        long fertilizer = findValueInMap(soil, almanac.getSoilToFertilizerMap());
        long water = findValueInMap(fertilizer, almanac.getFertilizerToWaterMap());
        long light = findValueInMap(water, almanac.getWaterToLightMap());
        long temperature = findValueInMap(light, almanac.getLightToTemperatureMap());
        long humidity = findValueInMap(temperature, almanac.getTemperatureToHumidityMap());
        return findValueInMap(humidity, almanac.getHumidityToLocationMap());
    }

    private static long findValueInMap(long value, List<RangeMapEntry> rangeMap) {
        return rangeMap.stream()
                .filter(rangeMapEntry -> valueInRange(value, rangeMapEntry.getSource(), rangeMapEntry.getRangeLength()))
                .findFirst()
                .map(rangeMapEntry -> rangeMapEntry.getDestination() + getPointInRange(value, rangeMapEntry.getSource()))
                .orElse(value);
    }

    private static boolean valueInRange(long value, long rangeStart, long rangeLength) {
        return rangeStart <= value && value < (rangeStart + rangeLength);
    }

    private static long getPointInRange(long seed, long rangeStart) {
        return seed - rangeStart;
    }
}
