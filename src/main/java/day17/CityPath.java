package main.java.day17;

import main.java.day16.domain.Pos;
import main.java.day17.domain.State;
import main.java.day17.domain.Work;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiFunction;

import static main.java.day17.domain.State.DOWN;
import static main.java.day17.domain.State.LEFT;
import static main.java.day17.domain.State.RIGHT;
import static main.java.day17.domain.State.UP;

public class CityPath {
    public static final Pos STARTING_POS = new Pos(0, 0);
    private final List<String> city = new ArrayList<>();
    private static final Map<Character, Set<Character>> DIRECTIONS = Map.of(
            UP, Set.of(UP, RIGHT, LEFT),
            LEFT, Set.of(LEFT, UP, DOWN),
            DOWN, Set.of(DOWN, LEFT, RIGHT),
            RIGHT, Set.of(RIGHT, UP, DOWN)
    );
    public long loadMap(Input input) {
        city.add(input.getCurrentLine());
        return 0;
    }

    public long start() {
        return followPath(1, CityPath::lessThan3StepsOrTurn);
    }

    public long phase2() {
        return followPath(4, CityPath::lessThan10StepsAndTurnOrAtLeast4StepsStraight);
    }

    private long followPath(int min, BiFunction<State, Character, Boolean> isValidNext) {
        Pos end = new Pos(city.getFirst().length() - 1, city.size() - 1);
        Set<State> visited = new HashSet<>();
        PriorityQueue<Work> queue = new PriorityQueue<>();

        State startingState = new State(STARTING_POS, RIGHT, 0);
        queue.add(new Work(startingState, 0));
        visited.add(startingState);

        while (!queue.isEmpty()) {
            Work work = queue.poll();
            if (work.state().location().equals(end) && work.state().singleDirection() >= min) {
                return work.heatLoss();
            }

            DIRECTIONS.get(work.state().direction()).stream()
                    .filter(direction -> positionInCity(work.state().next(direction).location()))
                    .filter(direction -> isValidNext.apply(work.state(), direction))
                    .map(direction -> work.state().next(direction))
                    .filter(state -> !visited.contains(state))
                    .forEach(state -> {
                        queue.add(new Work(state, (work.heatLoss()) + getTileAtPos(state.location())));
                        visited.add(state);
                    });
        }
        throw new IllegalStateException("No route found");
    }

    private static boolean lessThan3StepsOrTurn(State state, char nextDirection) {
        return state.singleDirection() < 3 || state.direction() != nextDirection;
    }

    private static boolean lessThan10StepsAndTurnOrAtLeast4StepsStraight(State state, char nextDirection) {
        if (state.singleDirection() > 9) {
            return state.direction() != nextDirection;
        } else if (state.singleDirection() <= 3) {
            return state.direction() == nextDirection;
        } else {
            return true;
        }
    }

    private int getTileAtPos(Pos pos) {
        return positionInCity(pos) ? Character.getNumericValue(city.get(pos.getRow()).charAt(pos.getCol())) : -1;
    }

    private boolean positionInCity(Pos pos) {
        return (pos.getRow() >= 0 && pos.getCol() >= 0 && pos.getRow() < city.size() && pos.getCol() < city.getFirst().length());
    }
}
