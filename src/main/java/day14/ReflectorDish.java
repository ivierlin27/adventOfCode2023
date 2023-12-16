package main.java.day14;

import main.java.domain.Input;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectorDish {
    private static final char ROUND_ROCK = 'O';
    private static final char CUBE_ROCK = '#';
    private static final char EMPTY = '.';
    private final Map<Integer, char[]> board = new LinkedHashMap<>();
    private final Map<String, Long> cycle = new LinkedHashMap<>();

    private int lineNumber = 0;

    public long loadBoard(Input input) {
        board.put(lineNumber, input.getCurrentLine().toCharArray());
        lineNumber++;
        return 0;
    }

    public void cycle(int numTimes) {
        for (long i = 0; i < numTimes; i++) {
            tiltNorth();
            tiltWest();
            tiltSouth();
            tiltEast();
            String currentState = createStringFromBoard();

            if (cycle.containsKey(currentState)) {
                long delta = i - cycle.get(currentState);
                i += delta * ((1000000000 - i) / delta);
            }
            cycle.put(currentState, i);
        }
    }

    public String createStringFromBoard() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board.values()) {
            for (char character : row) {
                sb.append(character);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void tiltNorth() {
        boolean moved = false;
        for (int i = 1; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length; j++) {
                if (board.get(i)[j] == ROUND_ROCK) {
                    int minEmpty = i;
                    for (int k = i - 1; k >= 0; k--) {
                        if (board.get(k)[j] == EMPTY) {
                            minEmpty = k;
                            moved = true;
                        } else {
                            break;
                        }
                    }
                    if (moved) {
                        board.get(minEmpty)[j] = ROUND_ROCK;
                        board.get(i)[j] = EMPTY;
                        moved = false;
                    }
                }
            }
        }
    }

    public void tiltWest() {
        boolean moved = false;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 1; j < board.get(i).length; j++) {
                if (board.get(i)[j] == ROUND_ROCK) {
                    int minEmpty = j;
                    for (int k = j - 1; k >= 0; k--) {
                        if (board.get(i)[k] == EMPTY) {
                            minEmpty = k;
                            moved = true;
                        } else {
                            break;
                        }
                    }
                    if (moved) {
                        board.get(i)[minEmpty] = ROUND_ROCK;
                        board.get(i)[j] = EMPTY;
                        moved = false;
                    }
                }
            }
        }
    }

    public void tiltEast() {
        boolean moved = false;
        for (int i = 0; i < board.size(); i++) {
            for (int j = board.get(i).length - 2; j >= 0; j--) {
                if (board.get(i)[j] == ROUND_ROCK) {
                    int minEmpty = j;
                    for (int k = j + 1; k < board.get(i).length; k++) {
                        if (board.get(i)[k] == EMPTY) {
                            minEmpty = k;
                            moved = true;
                        } else {
                            break;
                        }
                    }
                    if (moved) {
                        board.get(i)[minEmpty] = ROUND_ROCK;
                        board.get(i)[j] = EMPTY;
                        moved = false;
                    }
                }
            }
        }
    }

    public void tiltSouth() {
        boolean moved = false;
        for (int i = board.size() - 2; i >= 0; i--) {
            for (int j = 0; j < board.get(i).length; j++) {
                if (board.get(i)[j] == ROUND_ROCK) {
                    int minEmpty = i;
                    for (int k = i + 1; k < board.size(); k++) {
                        if (board.get(k)[j] == EMPTY) {
                            minEmpty = k;
                            moved = true;
                        } else {
                            break;
                        }
                    }
                    if (moved) {
                        board.get(minEmpty)[j] = ROUND_ROCK;
                        board.get(i)[j] = EMPTY;
                        moved = false;
                    }
                }
            }
        }
    }

    public long calculateLoad() {
        long load = 0;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length; j++) {
                if (board.get(i)[j] == ROUND_ROCK) {
                    load += board.size() - i;
                }
            }
        }
        return load;
    }
}
