package main.java.day14;

import main.java.domain.Input;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectorDish {
    private static final char ROUND_ROCK = 'O';
    private static final char CUBE_ROCK = '#';
    private static final char EMPTY = '.';
    private final Map<Integer, String> board = new LinkedHashMap<>();

    private int lineNumber = 0;

    public long loadBoard(Input input) {
        board.put(lineNumber, input.getCurrentLine());
        lineNumber++;
        return 0;
    }

    public void tiltNorth() {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 1; i < board.size(); i++) {
                StringBuilder prevRow = new StringBuilder(board.get(i - 1));
                StringBuilder currRow = new StringBuilder(board.get(i));
                for (int j = 0; j < board.get(i).length(); j++) {
                    if (board.get(i).charAt(j) == ROUND_ROCK && board.get(i - 1).charAt(j) == EMPTY) {
                        prevRow.setCharAt(j, ROUND_ROCK);
                        currRow.setCharAt(j, EMPTY);
                        moved = true;
                    }
                }
                board.put(i - 1, prevRow.toString());
                board.put(i, currRow.toString());
            }
        }
    }

    public long calculateLoad() {
        long load = 0;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length(); j++) {
                if (board.get(i).charAt(j) == ROUND_ROCK) {
                    load += board.size() - i;
                }
            }
        }
        return load;
    }
}
