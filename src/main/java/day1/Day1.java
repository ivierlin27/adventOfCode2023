package main.java.day1;

import main.java.day1.parser.NumberParser;
import main.java.day1.parser.Phase1Parser;

import static main.java.FileProcessorUtil.processFile;

public class Day1 {
    private static final String INPUT_FILE = "src/main/resources/day1/input.txt";

    public static int phase1() {
        return processFile(Phase1Parser::parseNumbersFromText, INPUT_FILE);
    }

    public static int phase2() {
        return processFile(NumberParser::parseNumbersAndWordNumbersFromText, INPUT_FILE);
    }
}
