package main.java.day14;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 107951 @ 8:44pm 12/14
public class Day14 {
    private static final String INPUT_FILE = "src/main/resources/day14/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day14/example_input.txt";
    public static long phase1() {
        ReflectorDish reflectorDish = new ReflectorDish();
        processFile(reflectorDish::loadBoard, INPUT_FILE);
        reflectorDish.tiltNorth();
        return reflectorDish.calculateLoad();
    }
}
