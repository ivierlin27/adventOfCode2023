package main.java.day23;

import main.java.day21.domain.Direction;
import main.java.day23.domain.PosWithDistance;
import main.java.domain.Input;
import main.java.domain.Pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static main.java.day21.domain.Direction.EAST;
import static main.java.day21.domain.Direction.NORTH;
import static main.java.day21.domain.Direction.SOUTH;
import static main.java.day21.domain.Direction.WEST;
import static main.java.day21.domain.Direction.getDirectionBetweenPoints;

public class PathFinder {

    public static final char PATH = '.';
    private final List<String> walkingMap = new ArrayList<>();
    private Pos startingPoint;
    private Pos endingPoint;

    private long longest = 0L;
    public long loadMap(Input input) {
        walkingMap.add(input.getCurrentLine());
        if (startingPoint == null) {
            startingPoint = new Pos(walkingMap.getFirst().indexOf(PATH), 0);
        }
        if (input.isLastLine()) {
            endingPoint = new Pos(input.getCurrentLine().indexOf(PATH), walkingMap.size() - 1);
        }
        return 0;
    }

    public long findLongestPath() {
        Set<Pos> visited = new HashSet<>();

        return walkPath(startingPoint, 0, visited);
    }

    private long walkPath(Pos pos, long steps, Set<Pos> visited) {
        if (pos.equals(endingPoint)) {
            longest = Math.max(steps, longest);
            return longest;
        }
        visited.add(pos);
        nextLocations(pos).stream()
                .filter(posWithDistance -> !visited.contains(posWithDistance.pos()))
                .forEach(posWithDistance -> walkPath(posWithDistance.pos(), posWithDistance.distance() + steps, visited));
        visited.remove(pos);
        return longest;
    }

    private List<PosWithDistance> nextLocations(Pos pos) {
        return Direction.getPosAtEachCardinalNeighbor(pos).stream()
                .filter(direction -> pos.inBounds(walkingMap))
                .filter(newPos -> matchesDirection(pos, newPos))
                .map(newPos -> new PosWithDistance(newPos, 1))
                .toList();
    }

    private boolean matchesDirection(Pos currentPos, Pos newPos) {
        char tile = currentPos.getTileAtPos(walkingMap);
        return switch (tile) {
            case '^' -> NORTH == getDirectionBetweenPoints(currentPos, newPos);
            case '<' -> WEST == getDirectionBetweenPoints(currentPos, newPos);
            case 'v' -> SOUTH == getDirectionBetweenPoints(currentPos, newPos);
            case '>' -> EAST == getDirectionBetweenPoints(currentPos, newPos);
            case '.' -> true;
            default -> false;
        };
    }
}
