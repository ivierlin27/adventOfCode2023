package main.java.day1.parser;

public class BackwardParser extends NumberParser {
    public BackwardParser(String line) {
        this.line = line;
        this.initialValue = line.length() - 1;
        this.finalValue = 0;
    }

    public boolean evaluate(int val) {
        return val >= this.finalValue;
    }

    public int next(int val) {
        return val - 1;
    }

    public String concat(String val1, String val2) {
        return val2 + val1;
    }
}
