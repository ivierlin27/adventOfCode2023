package main.java.domain;

public class Input {
    int currentLineNumber;
    String currentLine;
    String nextLine;

    public Input(int currentLineNumber, String currentLine, String nextLine) {
        this.currentLineNumber = currentLineNumber;
        this.currentLine = currentLine;
        this.nextLine = nextLine;
    }

    public int getCurrentLineNumber() {
        return currentLineNumber;
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
