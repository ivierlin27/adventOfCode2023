package main.java.day1.parser;

import java.util.Optional;

public class Phase1Parser {
    public static int parseNumbersFromText(String line) {
        Optional<String> firstNumber = findFirstNumber(line);
        Optional<String> lastNumber = findLastNumber(line);
        if (firstNumber.isPresent() && lastNumber.isPresent()) {
            return Integer.parseInt(firstNumber.get() + lastNumber.get());
        } else {
            return 0;
        }
    }

    private static Optional<String> findFirstNumber(String line) {
        for (int x = 0; x < line.length(); x++) {
            char character = line.charAt(x);
            if (Character.isDigit(character)) {
                return Optional.of(String.valueOf(character));
            }
        }
        return Optional.empty();
    }

    private static Optional<String> findLastNumber(String line) {
        for (int x = line.length() - 1; x >= 0; x--) {
            char character = line.charAt(x);
            if (Character.isDigit(character)) {
                return Optional.of(String.valueOf(character));
            }
        }
        return Optional.empty();
    }
}
