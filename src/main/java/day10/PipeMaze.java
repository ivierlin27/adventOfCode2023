package main.java.day10;

import main.java.day10.domain.Step;
import main.java.day10.domain.Tile;
import main.java.domain.Input;

import java.util.HashMap;
import java.util.Map;

import static main.java.day10.domain.Tile.EW_HORIZONTAL;
import static main.java.day10.domain.Tile.NE_90_BEND;
import static main.java.day10.domain.Tile.NS_VERTICAL;
import static main.java.day10.domain.Tile.NW_90_BEND;
import static main.java.day10.domain.Tile.SE_90_BEND;
import static main.java.day10.domain.Tile.STARTING;
import static main.java.day10.domain.Tile.SW_90_BEND;


public class PipeMaze {

    public static final String DIRECTION_NORTH = "N";
    public static final String DIRECTION_EAST = "E";
    public static final String DIRECTION_SOUTH = "S";
    public static final String DIRECTION_WEST = "W";
    Map<Integer, String> grid = new HashMap<>();

    int lineNumber = 0;

    Tile start;

    public PipeMaze() {
    }

    public Tile getStart() {
        return start;
    }

    public long loadInputLine(Input input) {
        grid.put(lineNumber, input.getCurrentLine());
        if (input.getCurrentLine().contains(STARTING)) {
            start = new Tile(STARTING, input.getCurrentLine().indexOf(STARTING), lineNumber);
        }
        lineNumber++;
        return 0;
    }

    public void discoverStartPipeType() {
        if (isStartOpenToLeft()) {
            if (isStartOpenToRight()) {
                start = new Tile(EW_HORIZONTAL, start.getColumn(), start.getRow());
            } else if (isStartOpenToTop()) {
                start = new Tile(NW_90_BEND, start.getColumn(), start.getRow());
            } else if (isStartOpenToBottom()) {
                start = new Tile(SW_90_BEND, start.getColumn(), start.getRow());
            }
        } else if (isStartOpenToRight()) {
            if (isStartOpenToTop()) {
                start = new Tile(NE_90_BEND, start.getColumn(), start.getRow());
            } else if (isStartOpenToBottom()) {
                start = new Tile(SE_90_BEND, start.getColumn(), start.getRow());
            }
        } else if (isStartOpenToTop() && isStartOpenToBottom()) {
            start = new Tile(NS_VERTICAL, start.getColumn(), start.getRow());
        }
    }

    public boolean currentPositionNotStart(Tile currentPos) {
        return !(currentPos.getColumn() == start.getColumn() && currentPos.getRow() == start.getRow());
    }

    public Step takeStep(Tile currentPos, String fromDirection) {
        if (fromDirection.equals(DIRECTION_NORTH)) {
            if (currentPos.isOpenToSouth()) {
                return createStepMovingSouth(currentPos);
            } else if (currentPos.isOpenToWest()) {
                return createStepMovingWest(currentPos);
            } else if (currentPos.isOpenToEast()) {
                return createStepMovingEast(currentPos);
            }
        }
        if (fromDirection.equals(DIRECTION_EAST)) {
            if (currentPos.isOpenToWest()) {
                return createStepMovingWest(currentPos);
            } else if (currentPos.isOpenToNorth()) {
                return createStepMovingNorth(currentPos);
            } else if (currentPos.isOpenToSouth()) {
                return createStepMovingSouth(currentPos);
            }
        }
        if (fromDirection.equals(DIRECTION_SOUTH)) {
            if (currentPos.isOpenToNorth()) {
                return createStepMovingNorth(currentPos);
            } else if (currentPos.isOpenToWest()) {
                return createStepMovingWest(currentPos);
            } else if (currentPos.isOpenToEast()) {
                return createStepMovingEast(currentPos);
            }
        }
        if (fromDirection.equals(DIRECTION_WEST)) {
            if (currentPos.isOpenToEast()) {
                return createStepMovingEast(currentPos);
            } else if (currentPos.isOpenToNorth()) {
                return createStepMovingNorth(currentPos);
            } else if (currentPos.isOpenToSouth()) {
                return createStepMovingSouth(currentPos);
            }
        }
        // if here, we are at the start and need to find a direction to take
        if (currentPos.isOpenToNorth()) {
            return createStepMovingNorth(currentPos);
        } else if (currentPos.isOpenToEast()) {
            return createStepMovingEast(currentPos);
        } else if (currentPos.isOpenToSouth()) {
            return createStepMovingSouth(currentPos);
        } else { // open to the West!
            return createStepMovingWest(currentPos);
        }
    }

    private Step createStepMovingNorth(Tile currentPos) {
        return new Step(getGridValueAtPosition(currentPos.getColumn(), currentPos.getRow() - 1),
                currentPos,
                DIRECTION_SOUTH
        );
    }

    private Step createStepMovingEast(Tile currentPos) {
        return new Step(getGridValueAtPosition(currentPos.getColumn() + 1, currentPos.getRow()),
                currentPos,
                DIRECTION_WEST
        );
    }

    private Step createStepMovingSouth(Tile currentPos) {
        return new Step(getGridValueAtPosition(currentPos.getColumn(), currentPos.getRow() + 1),
                currentPos,
                DIRECTION_NORTH
        );
    }

    private Step createStepMovingWest(Tile currentPos) {
        return new Step(getGridValueAtPosition(currentPos.getColumn() - 1, currentPos.getRow()),
                currentPos,
                DIRECTION_EAST
        );
    }

    private boolean isStartOpenToLeft() {
        if (start.getColumn() == 0) {
            return false;
        }
        Tile leftOfStart = getGridValueAtPosition(start.getColumn() - 1, start.getRow());
        return leftOfStart.isOpenToEast();
    }

    private boolean isStartOpenToRight() {
        if (start.getColumn() == grid.get(start.getRow()).length() - 1) {
            return false;
        }
        Tile rightOfStart = getGridValueAtPosition(start.getColumn() + 1, start.getRow());
        return rightOfStart.isOpenToWest();
    }

    private boolean isStartOpenToTop() {
        if (start.getRow() == 0) {
            return false;
        }
        Tile aboveStart = getGridValueAtPosition(start.getColumn(), start.getRow() - 1);
        return aboveStart.isOpenToSouth();
    }

    private boolean isStartOpenToBottom() {
        if (start.getRow() == grid.size() - 1) {
            return false;
        }
        Tile belowStart = getGridValueAtPosition(start.getColumn(), start.getRow() + 1);
        return belowStart.isOpenToNorth();
    }

    private Tile getGridValueAtPosition(int column, int row) {
        return new Tile(String.valueOf(grid.get(row).charAt(column)), column, row);
    }
}
