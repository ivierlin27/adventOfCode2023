package main.java.day13;

import main.java.day13.domain.Mirror;
import main.java.domain.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Valley {
    public static final char ASH = '.';
    public static final char ROCKS = '#';
    private final List<Mirror> mirrors = new ArrayList<>();

    private List<String> tempMirror = new ArrayList<>();

    public long processLine(Input input) {
        if (!input.getCurrentLine().isBlank()) {
            tempMirror.add(input.getCurrentLine());
            if (input.isLastLine()) {
                mirrors.add(new Mirror(tempMirror));
                tempMirror = new ArrayList<>();
            }
        } else {
            mirrors.add(new Mirror(tempMirror));
            tempMirror = new ArrayList<>();
        }
        return 0;
    }

    public long summarizePatterns() {
        return mirrors.stream().map(this::evaluateMirror).findFirst().orElseThrow();
    }

    public long smudgePatterns() {
        return mirrors.stream().map(this::smudge).reduce(0L, Long::sum);
    }

    private long evaluateMirror(Mirror mirror) {
        List<String> cols = IntStream.range(0, mirror.getLayout().getFirst().length())
                .mapToObj(i -> mirror.getLayout().stream()
                        .map(row -> row.charAt(i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .toList();
        mirror.getSolutions().addAll(findReflectionPoint(cols, 1));
        mirror.getSolutions().addAll(findReflectionPoint(mirror.getLayout(), 100));
        return mirror.getSolutions().stream().findFirst().orElseThrow();
    }

    private List<Long> findReflectionPoint(List<String> strings, long factor) {
        return IntStream.range(1, strings.size())
                .filter(i -> strings.get(i - 1).equals(strings.get(i)))
                .filter(i -> verifyFullMatch(i, strings))
                .mapToObj(i -> factor * i)
                .toList();
    }

    private static boolean verifyFullMatch(final int i, List<String> strings) {
        int left = i - 1;
        int right = i;
        final int size = strings.size();
        while (left >= 0 && right < size) {
            if (!strings.get(left).equals(strings.get(right))) {
                return false;
            }
            --left;
            ++right;
        }
        return true;
    }

    private Stream<String> smudge(String string) {
        return IntStream.range(0, string.length())
                .mapToObj(i -> string.substring(0, i) +
                        toggle(string.charAt(i)) + string.substring(i + 1));
    }

    private char toggle(char c) {
        if (c == ASH) {
            return ROCKS;
        } else {
            return ASH;
        }
    }

    private long smudge(Mirror mirror) {
        evaluateMirror(mirror);
        long original = mirror.getSolutions().stream().findFirst().orElseThrow();
        IntStream.range(0, mirror.getLayout().size()).forEach(i -> {
            String save = mirror.getLayout().get(i);
            smudge(save).forEach(s -> {
                mirror.getLayout().set(i, s);
                evaluateMirror(mirror);
            });
            mirror.getLayout().set(i, save);
        });
        Set<Long> copy = new TreeSet<>(mirror.getSolutions());
        copy.remove(original);
        return copy.stream().findFirst().orElseThrow();
    }
}
