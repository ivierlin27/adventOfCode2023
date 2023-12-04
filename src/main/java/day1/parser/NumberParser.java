package main.java.day1.parser;

import main.java.domain.Input;

import java.util.Map;
import java.util.Optional;

public abstract class NumberParser {
    protected String line;
    protected int initialValue;
    protected int finalValue;
    private static final Map<String, String> NUMBER_WORD_MAP = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    protected abstract boolean evaluate(int var1);

    protected abstract int next(int var1);

    protected abstract String concat(String var1, String var2);

    public static int parseNumbersAndWordNumbersFromText(Input input) {
        Optional<String> firstValue = (new ForwardParser(input.getCurrentLine())).findValue();
        Optional<String> lastValue = (new BackwardParser(input.getCurrentLine())).findValue();
        if (firstValue.isPresent() && lastValue.isPresent()) {
            return Integer.parseInt(firstValue.get() + lastValue.get());
        } else {
            return 0;
        }
    }

    protected Optional<String> findValue() {
        String temp = "";

        for (int x = this.initialValue; this.evaluate(x); x = this.next(x)) {
            char character = this.line.charAt(x);
            if (Character.isDigit(character)) {
                return Optional.of(Character.toString(character));
            }

            temp = this.concat(temp, String.valueOf(character));
            Optional<String> num = numberStringFromNumberWord(temp);
            if (num.isPresent()) {
                return num;
            }
        }

        return Optional.empty();
    }

    private static Optional<String> numberStringFromNumberWord(String word) {
        for (Map.Entry<String, String> entry : NUMBER_WORD_MAP.entrySet()) {
            if (word.contains(entry.getKey())) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }
}
