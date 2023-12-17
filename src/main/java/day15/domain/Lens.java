package main.java.day15.domain;

public class Lens {
    String source;
    String label;
    int box;
    char operator;
    Integer focalLength;

    public Lens(String source, String label, int box, char operator) {
        this.source = source;
        this.label = label;
        this.box = box;
        this.operator = operator;
    }

    public Lens(String source, String label, int box, char operator, Integer focalLength) {
        this.source = source;
        this.label = label;
        this.box = box;
        this.operator = operator;
        this.focalLength = focalLength;
    }

    public String getSource() {
        return source;
    }

    public String getLabel() {
        return label;
    }

    public int getBox() {
        return box;
    }

    public char getOperator() {
        return operator;
    }

    public Integer getFocalLength() {
        return focalLength;
    }
}
