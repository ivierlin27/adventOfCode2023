package main.java.day5.domain;

import main.java.domain.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Almanac {
    private List<Long> seeds;
    private final List<RangeMapEntry> seedToSoilMap = new ArrayList<>();
    private final List<RangeMapEntry> soilToFertilizerMap = new ArrayList<>();
    private final List<RangeMapEntry> fertilizerToWaterMap = new ArrayList<>();
    private final List<RangeMapEntry> waterToLightMap = new ArrayList<>();
    private final List<RangeMapEntry> lightToTemperatureMap = new ArrayList<>();
    private final List<RangeMapEntry> temperatureToHumidityMap = new ArrayList<>();
    private final List<RangeMapEntry> humidityToLocationMap = new ArrayList<>();

    public Almanac() {
    }

    public List<Long> getSeeds() {
        return seeds;
    }

    public List<RangeMapEntry> getSeedToSoilMap() {
        return seedToSoilMap;
    }

    public List<RangeMapEntry> getSoilToFertilizerMap() {
        return soilToFertilizerMap;
    }

    public List<RangeMapEntry> getFertilizerToWaterMap() {
        return fertilizerToWaterMap;
    }

    public List<RangeMapEntry> getWaterToLightMap() {
        return waterToLightMap;
    }

    public List<RangeMapEntry> getLightToTemperatureMap() {
        return lightToTemperatureMap;
    }

    public List<RangeMapEntry> getTemperatureToHumidityMap() {
        return temperatureToHumidityMap;
    }

    public List<RangeMapEntry> getHumidityToLocationMap() {
        return humidityToLocationMap;
    }

    public int parseSeeds(Input input) {
        if (input.getCurrentLine().startsWith("seeds")) {
            seeds = Arrays.stream(input.getCurrentLine()
                            .split(":")[1].trim()
                            .split("\\s+"))
                    .map(Long::parseLong)
                    .toList();
        }
        return 0;
    }

    public int parseSeedToSoil(Input input) {
        addToMap(input.getCurrentLine(), seedToSoilMap);
        return 0;
    }

    public int parseSoilToFertilizer(Input input) {
        addToMap(input.getCurrentLine(), soilToFertilizerMap);
        return 0;
    }

    public int parseFertilizerToWater(Input input) {
        addToMap(input.getCurrentLine(), fertilizerToWaterMap);
        return 0;
    }

    public int parseWaterToLight(Input input) {
        addToMap(input.getCurrentLine(), waterToLightMap);
        return 0;
    }

    public int parseLightToTemperature(Input input) {
        addToMap(input.getCurrentLine(), lightToTemperatureMap);
        return 0;
    }

    public int parseTemperatureToHumidity(Input input) {
        addToMap(input.getCurrentLine(), temperatureToHumidityMap);
        return 0;
    }

    public int parseHumidityToLocation(Input input) {
        addToMap(input.getCurrentLine(), humidityToLocationMap);
        return 0;
    }

    private void addToMap(String line, List<RangeMapEntry> map) {
        String[] parts = line.split("\\s+");
        map.add(new RangeMapEntry(parts));
    }
}
