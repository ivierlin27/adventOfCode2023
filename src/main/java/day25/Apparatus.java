package main.java.day25;

import main.java.domain.Input;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Apparatus {
    SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    public long processInput(Input input) {
        String[] parts = input.getCurrentLine().split(":");
        List<String> componentParts = Arrays.stream(parts[1].trim().split(" ")).toList();
        graph.addVertex(parts[0]);
        componentParts.forEach(part -> {
            graph.addVertex(part);
            graph.addEdge(parts[0], part);
        });

        if (input.isLastLine()) {
            Set<String> side = new StoerWagnerMinimumCut<>(graph).minCut();

            return (long) (graph.vertexSet().size() - side.size()) * side.size();
        }
        return 0;
    }
}
