package main.java.day3;

import main.java.day3.domain.Line;
import main.java.day3.domain.Number;
import main.java.day3.domain.Symbol;

import java.util.ArrayList;
import java.util.List;

public class EngineDiagramParser {
    private static final Character JUNK = '.';
    protected static Line parseLine(int currentLineNumber, String line) {
        StringBuilder temp = new StringBuilder();
        List<Number> numbers = new ArrayList<>();
        List<Symbol> symbols = new ArrayList<>();

        for (int x = 0; x < line.length(); x++) {
            char character = line.charAt(x);
            if (Character.isDigit(character)) {
                temp.append(character);
                if (lastCharacterInLine(x + 1, line) ||
                        nextCharacterNotNumber(x + 1, line)) {
                    numbers.add(createNumber(x, temp));
                    temp = new StringBuilder();
                }
            } else if (character != JUNK) {
                symbols.add(new Symbol(currentLineNumber, x));
            }
        }

        return new Line(numbers, symbols);
    }

    private static boolean lastCharacterInLine(int nextCharacterIndex, String line) {
        return nextCharacterIndex >= line.length();
    }

    private static boolean nextCharacterNotNumber(int nextCharacterIndex, String line) {
        return !Character.isDigit(line.charAt(nextCharacterIndex));
    }

    private static int posAtBeginningOfNumber(int posAtEnd, StringBuilder numberString) {
        return posAtEnd - numberString.length() + 1;
    }

    private static Number createNumber(int currentPosition, StringBuilder numberString) {
        return new Number(Integer.parseInt(numberString.toString()), numberString.length(), posAtBeginningOfNumber(currentPosition,  numberString));
    }
}
