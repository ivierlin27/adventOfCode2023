package main.java.day3.domain;

public class Symbol {
    int position;
    int line;

    public Symbol(int line, int position) {
        this.line = line;
        this.position = position;
    }

    public Symbol(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getLine() {
        return line;
    }
}
