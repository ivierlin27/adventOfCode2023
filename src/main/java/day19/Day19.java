package main.java.day19;

import static main.java.FileProcessorUtil.processFile;

// Phase 1: 391132 @ 4:52pm 12/19 (correct!)
// Phase 2: 128163929109524 @ 8:33pm 12/19 (correct!)
public class Day19 {
    private static final String INPUT_FILE = "src/main/resources/day19/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day19/example_input.txt";

    public static long phase1() {
        WorkflowProcessor workflowProcessor = new WorkflowProcessor();
        processFile(workflowProcessor::readInputLine, INPUT_FILE);
        return workflowProcessor.processParts();
    }

    public static long phase2() {
        WorkflowProcessor workflowProcessor = new WorkflowProcessor();
        processFile(workflowProcessor::readInputLine, INPUT_FILE);
        return workflowProcessor.findAcceptedCombinations();
    }
}
