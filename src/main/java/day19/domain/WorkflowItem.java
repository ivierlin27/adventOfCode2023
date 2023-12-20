package main.java.day19.domain;

public record WorkflowItem(char character, char operation, long num, String result) {
    public int indexFromCharacter() {
        return switch (character) {
            case 'x' -> 0;
            case 'm' -> 1;
            case 'a' -> 2;
            case 's' -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
}
