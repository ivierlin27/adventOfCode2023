package main.java.day17;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 1128 @ 11:29am 12/17 (correct!)
public class Day17 {
    private static final String INPUT_FILE = "src/main/resources/day17/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day17/example_input.txt";
    public static long phase1() {
        CityPath cityPath = new CityPath();
        processFile(cityPath::loadMap, INPUT_FILE);
        return cityPath.start();
    }
}
