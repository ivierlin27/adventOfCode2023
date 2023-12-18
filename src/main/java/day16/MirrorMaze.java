package main.java.day16;

import main.java.day16.domain.Pos;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MirrorMaze {
    public static final char EAST = 'e';
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

    public long start(Pos startingPos, char direction) {
        Map<Pos, Boolean> energized = new HashMap<>();
        followBeam(energized, direction, startingPos);

        return calculateEnergized(energized);
    }

    public long phase2() {
        List<Long> energizedValues = new ArrayList<>();
        for (int i = 0; i < layout.size(); i++) {
            energizedValues.add(start(new Pos(0, i), EAST));
            energizedValues.add(start(new Pos(layout.getFirst().length() - 1, i), WEST));
        }
        for (int i = 0; i < layout.getFirst().length(); i++) {
            energizedValues.add(start(new Pos(i, 0), SOUTH));
            energizedValues.add(start(new Pos(i, layout.size() - 1), NORTH));
        }
        return energizedValues.stream().max(Long::compareTo).orElseThrow();
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
        return (pos.row() < 0 || pos.col() < 0 || pos.row() >= layout.size() || pos.col() >= layout.getFirst().length());
    }

    private long calculateEnergized(Map<Pos, Boolean> energized) {
        return energized.entrySet().stream().filter(Map.Entry::getValue).count();
    }

    private Pos move(char direction, Pos currentPos) {
        return switch (direction) {
            case EAST -> new Pos(currentPos.col() + 1, currentPos.row());
            case SOUTH -> new Pos(currentPos.col(), currentPos.row() + 1);
            case WEST -> new Pos(currentPos.col() - 1, currentPos.row());
            case NORTH -> new Pos(currentPos.col(), currentPos.row() - 1);
            default -> null;
        };
    }

    private char getTileAtPos(Pos pos) {
        return layout.get(pos.row()).charAt(pos.col());
    }
}
