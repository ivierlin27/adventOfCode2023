package main.java.day3.domain;

public class Number {
    int value;
    int length;
    int position;
    Symbol enginePartSymbol;
    boolean isEnginePart;

    public Number(int value, int length, int position) {
        this.value = value;
        this.length = length;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    public int getPosition() {
        return position;
    }

    public boolean isEnginePart() {
        return isEnginePart;
    }

    public void setEnginePart(boolean enginePart) {
        isEnginePart = enginePart;
    }

    public Symbol getEnginePartSymbol() {
        return enginePartSymbol;
    }

    public void setEnginePartSymbol(Symbol enginePartSymbol) {
        this.enginePartSymbol = enginePartSymbol;
    }
}
