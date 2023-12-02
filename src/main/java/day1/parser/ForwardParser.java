package main.java.day1.parser;

public class ForwardParser extends NumberParser {
    public ForwardParser(String line) {
        this.line = line;
        this.initialValue = 0;
        this.finalValue = line.length();
    }

    public boolean evaluate(int val) {
        return val < this.finalValue;
    }

    public int next(int val) {
        return val + 1;
    }

    public String concat(String val1, String val2) {
        return val1 + val2;
    }
}
