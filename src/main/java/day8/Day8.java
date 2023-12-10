package main.java.day8;

import main.java.day8.domain.NodeMap;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 18673 @ 5:15pm 12/9 (correct!)
// Phase 2: 17972669116327 @ 7:32pm 12/9 (correct!)
public class Day8 {
    private static final String INPUT_FILE = "src/main/resources/day8/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day8/example_input.txt";
    private static final String EXAMPLE_INPUT_PHASE2_FILE = "src/main/resources/day8/example_input_phase2.txt";
    public static long phase1() {
        NodeMap nodeMap = new NodeMap();
        processFile(nodeMap::parseInput, INPUT_FILE);
        return Phase1.followPath(nodeMap);
    }

    public static long phase2() {
        NodeMap nodeMap = new NodeMap();
        processFile(nodeMap::parseInput, INPUT_FILE);
        return Phase2.followPaths(nodeMap);
    }
}
