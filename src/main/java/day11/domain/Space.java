package main.java.day11.domain;

import main.java.domain.Input;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Space {
    public static final String GALAXY = "#";
    private final Map<Integer, String> grid = new LinkedHashMap<>();
    private final List<Galaxy> galaxies = new ArrayList<>();
    private final List<Integer> rowsToExpand = new ArrayList<>();
    private final List<Integer> columnsToExpand = new ArrayList<>();
    private int lineNumber = 0;

    public long loadInputLine(Input input) {
        grid.put(lineNumber, input.getCurrentLine());
        lineNumber++;
        if (input.isLastLine()) {
            locateGalaxies(grid, galaxies);
            rowsToExpand.addAll(calculateRowsToExpand());
            columnsToExpand.addAll(calculateColumnsToExpand());
        }
        return 0;
    }

    public long calculateShortestPaths(int expansionMultiplier) {
        long total = 0;
        for (int x = 0; x < galaxies.size(); x++) {
            for (int y = x + 1; y < galaxies.size(); y++) {
                total += calculatePath(galaxies.get(x), galaxies.get(y), expansionMultiplier);
            }
        }
        return total;
    }

    private long calculatePath(Galaxy galaxy, Galaxy otherGalaxy, int expansionMultiplier) {
        long columnMultiplierCount = 0;
        for (Integer column : columnsToExpand) {
            if (Math.min(galaxy.column, otherGalaxy.column) < column && Math.max(galaxy.column, otherGalaxy.column) > column) {
                columnMultiplierCount++;
            }
        }
        long rowMultiplierCount = 0;
        for (Integer row : rowsToExpand) {
            if (Math.min(galaxy.row, otherGalaxy.row) < row && Math.max(galaxy.row, otherGalaxy.row) > row) {
                rowMultiplierCount++;
            }
        }
        return Math.abs(galaxy.column - otherGalaxy.column) + Math.abs(galaxy.row - otherGalaxy.row) +
                ((columnMultiplierCount + rowMultiplierCount) * (expansionMultiplier - 1));
    }

    private List<Integer> calculateRowsToExpand() {
        return grid.entrySet().stream()
                .filter(entry -> !entry.getValue().contains(GALAXY))
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Integer> calculateColumnsToExpand() {
        List<Integer> columnsWithGalaxies = galaxies.stream().map(galaxy -> galaxy.column).toList();
        List<Integer> columnsToExpand = new ArrayList<>();
        for (int i = 0; i < grid.get(0).length(); i++) {
            if (!columnsWithGalaxies.contains(i)) {
                columnsToExpand.add(i);
            }
        }
        return columnsToExpand;
    }

    private void locateGalaxies(Map<Integer, String> gridToProcess, List<Galaxy> galaxiesContainer) {
        gridToProcess.forEach((key, value) -> {
            for (int i = 0; i < gridToProcess.get(0).length(); i++) {
                char character = value.charAt(i);
                if (character == '#') {
                    galaxiesContainer.add(new Galaxy(i, key));
                }
            }
        });
    }
}
