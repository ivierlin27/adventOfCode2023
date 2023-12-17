package main.java.day16;

import main.java.day16.domain.Pos;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MirrorMaze {
    private static final char EAST = 'e';
    private static final char SOUTH = 's';
    private static final char WEST = 'w';
    private static final char NORTH = 'n';
    private static final char EMPTY = '.';
    private static final char V_SPLITTER = '|';
    private static final char H_SPLITTER = '-';
    private static final char UP_MIRROR = '/';
    private static final char DOWN_MIRROR = '\\';
    List<String> layout =  new ArrayList<>();
    public long loadLayout(Input input) {
        layout.add(input.getCurrentLine());
        return 0;
    }

    public long start() {
        Map<Pos, Boolean> energized = new HashMap<>();
        Pos currentPos = new Pos(0, 0);
        followBeam(energized, EAST, currentPos);

        return calculateEnergized(energized);
    }

    private boolean splitterAlreadyTouched(Map<Pos, Boolean> energized, Pos pos) {
        char tile = getTileAtPos(pos);
        return (tile == V_SPLITTER || tile == H_SPLITTER) && energized.containsKey(pos);
    }

    private void followBeam(Map<Pos, Boolean> energized, char direction, Pos currentPos) {
        if (posOffBoard(currentPos) || splitterAlreadyTouched(energized, currentPos)) {
            return;
        } else {
            energized.put(currentPos, true);
            switch (getTileAtPos(currentPos)) {
                case V_SPLITTER -> {
                    switch (direction) {
                        case EAST, WEST -> {
                            followBeam(energized, NORTH, move(NORTH, currentPos));
                            followBeam(energized, SOUTH, move(SOUTH, currentPos));
                        }
                        case NORTH, SOUTH -> followBeam(energized, direction, move(direction, currentPos));
                    }
                }
                case H_SPLITTER -> {
                    switch (direction) {
                        case EAST, WEST -> followBeam(energized, direction, move(direction, currentPos));
                        case NORTH, SOUTH -> {
                            followBeam(energized, EAST, move(EAST, currentPos));
                            followBeam(energized, WEST, move(WEST, currentPos));
                        }
                    }
                }
                case UP_MIRROR -> {
                    switch (direction) {
                        case EAST -> followBeam(energized, NORTH, move(NORTH, currentPos));
                        case SOUTH -> followBeam(energized, WEST, move(WEST, currentPos));
                        case WEST -> followBeam(energized, SOUTH, move(SOUTH, currentPos));
                        case NORTH -> followBeam(energized, EAST, move(EAST, currentPos));
                    }
                }
                case DOWN_MIRROR -> {
                    switch (direction) {
                        case EAST -> followBeam(energized, SOUTH, move(SOUTH, currentPos));
                        case SOUTH -> followBeam(energized, EAST, move(EAST, currentPos));
                        case WEST -> followBeam(energized, NORTH, move(NORTH, currentPos));
                        case NORTH -> followBeam(energized, WEST, move(WEST, currentPos));
                    }
                }
                default -> followBeam(energized, direction, move(direction, currentPos));
            }
        }
    }

    private boolean posOffBoard(Pos pos) {
        return (pos.getRow() < 0 || pos.getCol() < 0 || pos.getRow() >= layout.size() || pos.getCol() >= layout.getFirst().length());
    }

    private long calculateEnergized(Map<Pos, Boolean> energized) {
        return energized.entrySet().stream().filter(Map.Entry::getValue).count();
    }

    private Pos move(char direction, Pos currentPos) {
        return switch (direction) {
            case EAST -> new Pos(currentPos.getCol() + 1, currentPos.getRow());
            case SOUTH -> new Pos(currentPos.getCol(), currentPos.getRow() + 1);
            case WEST -> new Pos(currentPos.getCol() - 1, currentPos.getRow());
            case NORTH -> new Pos(currentPos.getCol(), currentPos.getRow() - 1);
            default -> null;
        };
    }

    private char getTileAtPos(Pos pos) {
        return layout.get(pos.getRow()).charAt(pos.getCol());
    }
}
