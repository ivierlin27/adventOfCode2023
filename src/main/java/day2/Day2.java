package main.java.day2;

import main.java.day2.domain.Game;
import main.java.domain.Input;

import static main.java.FileProcessorUtil.processFile;
import static main.java.day2.PowerCalculator.calculateGamePower;
import static main.java.day2.parser.GameParser.parseGame;

public class Day2 {

    private static final String INPUT_FILE = "src/main/resources/day2/input.txt";
    // Use the example_input to check based on the problem description example
    private static final String EXAMPLE_INPUT_FILE = "src/main/resources/day2/example_input.txt";
    public static long phase1() {
        return processFile(Day2::parseGameValidity, INPUT_FILE);
    }

    public static long phase2() {
        return processFile(Day2::calculateFewestStones, INPUT_FILE);
    }

    private static long parseGameValidity(Input input) {
        Game game = parseGame(input.getCurrentLine());
        if (Validator.isValid(game)) {
            return game.getNumber();
        }
        return 0;
    }

    private static long calculateFewestStones(Input input) {
        Game game = parseGame(input.getCurrentLine());
        return calculateGamePower(game);
    }
}
