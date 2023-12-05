package main.java.day5;

import main.java.day5.domain.Almanac;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 29588814 @ 9:20am
// Phase 1: 29588820 @ 9:32am
// Phase 1: 261668924 @ 9:38am
public class Day5 {
    private static final String SEEDS_INPUT_FILE = "src/main/resources/day5/seeds.txt";
    private static final String SEED_TO_SOIL_INPUT_FILE = "src/main/resources/day5/seed-to-soil.txt";
    private static final String SOIL_TO_FERTILIZER_INPUT_FILE = "src/main/resources/day5/soil-to-fertilizer.txt";
    private static final String FERTILIZER_TO_WATER_INPUT_FILE = "src/main/resources/day5/fertilizer-to-water.txt";
    private static final String WATER_TO_LIGHT_INPUT_FILE = "src/main/resources/day5/water-to-light.txt";
    private static final String LIGHT_TO_TEMPERATURE_INPUT_FILE = "src/main/resources/day5/light-to-temperature.txt";
    private static final String TEMPERATURE_TO_HUMIDITY_INPUT_FILE = "src/main/resources/day5/temperature-to-humidity.txt";
    private static final String HUMIDITY_TO_LOCATION_INPUT_FILE = "src/main/resources/day5/humidity-to-location.txt";

    public static long phase1() {
        Almanac almanac = new Almanac();
        loadFiles(almanac);

        return Phase1.findLowestLocation(almanac);
    }

    private static void loadFiles(Almanac almanac) {
        processFile(almanac::parseSeeds, SEEDS_INPUT_FILE);
        processFile(almanac::parseSeedToSoil, SEED_TO_SOIL_INPUT_FILE);
        processFile(almanac::parseSoilToFertilizer, SOIL_TO_FERTILIZER_INPUT_FILE);
        processFile(almanac::parseFertilizerToWater, FERTILIZER_TO_WATER_INPUT_FILE);
        processFile(almanac::parseWaterToLight, WATER_TO_LIGHT_INPUT_FILE);
        processFile(almanac::parseLightToTemperature, LIGHT_TO_TEMPERATURE_INPUT_FILE);
        processFile(almanac::parseTemperatureToHumidity, TEMPERATURE_TO_HUMIDITY_INPUT_FILE);
        processFile(almanac::parseHumidityToLocation, HUMIDITY_TO_LOCATION_INPUT_FILE);
    }
}
