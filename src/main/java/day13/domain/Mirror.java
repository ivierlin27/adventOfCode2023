package main.java.day13.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Mirror {
    List<String> layout;

    private final Set<Long> solutions = new TreeSet<>();

    public Mirror(List<String> layout) {
        this.layout = layout;
    }

    public List<String> getLayout() {
        return layout;
    }

    public Set<Long> getSolutions() {
        return solutions;
    }
}
