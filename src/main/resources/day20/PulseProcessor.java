package main.resources.day20;

import main.java.domain.Input;
import main.resources.day20.domain.Broadcast;
import main.resources.day20.domain.Conjunction;
import main.resources.day20.domain.Counter;
import main.resources.day20.domain.FlipFlop;
import main.resources.day20.domain.Module;
import main.resources.day20.domain.ModuleType;
import main.resources.day20.domain.WorkItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static main.resources.day20.domain.ModuleType.BROADCAST;
import static main.resources.day20.domain.ModuleType.CONJUNCTION;
import static main.resources.day20.domain.ModuleType.FLIP_FLOP;
import static main.resources.day20.domain.PulseType.HIGH;
import static main.resources.day20.domain.PulseType.LOW;

public class PulseProcessor {

    Map<String, Module> modules = new HashMap<>();
    public long loadModules(Input input) {
        String[] parts = input.getCurrentLine().split(" ");
        char first = parts[0].charAt(0);
        ModuleType type = switch (first) {
            case '%' -> FLIP_FLOP;
            case '&' -> CONJUNCTION;
            default -> BROADCAST;
        };
        String name = type.equals(BROADCAST) ? parts[0] : parts[0].substring(1);
        List<String> destinations = new ArrayList<>();
        for (int i = 2; i < parts.length; i++) {
            destinations.add(parts[i].replace(",", ""));
        }
        switch (type) {
            case BROADCAST -> modules.put(name, new Broadcast(name, destinations));
            case FLIP_FLOP -> modules.put(name, new FlipFlop(name, destinations));
            case CONJUNCTION -> modules.put(name, new Conjunction(name, destinations));
        }
        if (input.isLastLine()) {
            modules.values().stream().filter(module -> module instanceof Conjunction)
                    .forEach(conjunction -> {
                        List<String> inputModules = modules.values().stream()
                                .filter(module -> module.getDestinations().contains(conjunction.getName()))
                                .map(Module::getName)
                                .toList();
                        inputModules.forEach(inputModule -> ((Conjunction) conjunction).addMemory(Map.entry(inputModule, LOW)));
                    });
        }
        return 0;
    }

    public long pushButton(int numTimes) {
        Counter counter = new Counter(0, 0);
        IntStream.range(0, numTimes).forEach(x -> pushButtonOnce(counter));
        return counter.score();
    }

    public void pushButtonOnce(Counter counter) {
        LinkedList<WorkItem> queue = new LinkedList<>(List.of(new WorkItem("broadcaster", LOW, "")));

        while (!queue.isEmpty()) {
            WorkItem work = queue.removeFirst();
            if (work.pulseType().equals(LOW)) {
                counter.incrementLow();
            } else if (work.pulseType().equals(HIGH)) {
                counter.incrementHigh();
            }
            if (modules.containsKey(work.destination())) {
                Module module = modules.get(work.destination());
                module.input(work.source(), work.pulseType(), queue);
            }
        }
    }
}
