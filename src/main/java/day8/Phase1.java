package main.java.day8;

import main.java.day8.domain.Node;
import main.java.domain.Input;

import java.util.HashMap;
import java.util.Map;

public class Phase1 {
    String directions;
    Map<String, Node> nodes = new HashMap<>();

    boolean parsedDirectionsAlready = false;
    public long parseInput(Input input) {
        if (!input.getCurrentLine().isEmpty()) {
            if (parsedDirectionsAlready) {
                String[] parts = input.getCurrentLine().split(" = \\(");

                String[] lrParts = parts[1].replace(")", "").split(",");

                nodes.put(parts[0], new Node(lrParts[0].trim(), lrParts[1].trim()));
            } else {
                directions = input.getCurrentLine();
                parsedDirectionsAlready = true;
            }
        }


        return 0;
    }

    public long followPath() {
        String currentLocation = "AAA";
        int x = 0;
        int steps = 0;
        while (!currentLocation.equals("ZZZ")) {
            steps++;
            char direction = directions.charAt(x);
            Node node = nodes.get(currentLocation);
            if (direction == 'L') {
                currentLocation = node.getLeft();
            } else if (direction == 'R') {
                currentLocation = node.getRight();
            }
            if (x + 1 > directions.length() - 1) {
                x = 0;
            } else {
                x++;
            }
        }
        return steps;
    }
}
