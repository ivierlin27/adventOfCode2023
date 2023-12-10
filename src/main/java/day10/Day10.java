package main.java.day10;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 6942 @ 1:30pm 12/10 (correct!)
// Phase 2: 297 @ 3:44pm 12/10 (correct!)
public class Day10 {
    private static final String INPUT_FILE = "src/main/resources/day10/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day10/example_input.txt";
    private static final String EXAMPLE_INPUT_COMPLEX_FILE = "src/main/resources/day10/example_input_complex.txt";

    public static long phase1() {
        PipeMaze pipeMaze = new PipeMaze();
        processFile(pipeMaze::loadInputLine, INPUT_FILE);
        pipeMaze.discoverStartPipeType();

        return Phase1.countStepsInPipe(pipeMaze) / 2;
    }

    public static long phase2() {
        PipeMaze pipeMaze = new PipeMaze();
        processFile(pipeMaze::loadInputLine, INPUT_FILE);
        pipeMaze.discoverStartPipeType();

        return Phase2.countEnclosedTiles(pipeMaze);
    }
}
