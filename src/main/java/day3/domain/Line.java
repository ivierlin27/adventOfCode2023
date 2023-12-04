package main.java.day3.domain;

import java.util.List;

public class Line {
    List<Number> numbers;
    List<Symbol> symbols;

    public Line(List<Number> numbers, List<Symbol> symbols) {
        this.numbers = numbers;
        this.symbols = symbols;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }
}
