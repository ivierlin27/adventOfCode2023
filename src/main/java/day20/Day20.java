package main.java.day20;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 1009142 @ 9:58pm 12/20 (too low)
// Phase 1: 899848294 @ 10:16pm 12/21 (correct!)
// Phase 2 247454898168563 @ 5:07pm 12/22 (correct!)
public class Day20 {
    private static final String INPUT_FILE = "src/main/resources/day20/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day20/example_input.txt";

    public static long phase1() {
        PulseProcessor pulseProcessor = new PulseProcessor();
        processFile(pulseProcessor::loadModules, INPUT_FILE);
        return pulseProcessor.pushButton(1000);
    }

    public static long phase2() {
        PulseProcessor pulseProcessor = new PulseProcessor();
        processFile(pulseProcessor::loadModules, INPUT_FILE);
        return pulseProcessor.pushButtonForRx();
    }
}
