package main.java.day8.domain;

import main.java.domain.Input;

import java.util.HashMap;
import java.util.Map;

public class NodeMap {

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

    public String getDirections() {
        return directions;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }
}
