package main.java.domain;

public class Input {
    String currentLine;
    String nextLine;

    public Input(String currentLine, String nextLine) {
        this.currentLine = currentLine;
        this.nextLine = nextLine;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public String getNextLine() {
        return nextLine;
    }

    public boolean isLastLine() {
        return nextLine == null;
    }
}
