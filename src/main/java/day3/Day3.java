package main.java.day3;

import static main.java.FileProcessorUtil.processFile;

public class Day3 {

    private static final String INPUT_FILE = "src/main/resources/day3/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day3/example_input.txt";
    public static int phase1() {
        Phase1 phase1 = new Phase1();
        return processFile(phase1::parseEngineDiagram, INPUT_FILE);
    }
}
