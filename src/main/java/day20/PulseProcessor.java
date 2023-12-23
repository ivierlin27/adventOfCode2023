package main.java.day20;

import main.java.domain.Input;
import main.java.day20.domain.Broadcast;
import main.java.day20.domain.Conjunction;
import main.java.day20.domain.Counter;
import main.java.day20.domain.FlipFlop;
import main.java.day20.domain.Module;
import main.java.day20.domain.ModuleType;
import main.java.day20.domain.WorkItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static main.java.day20.domain.ModuleType.BROADCAST;
import static main.java.day20.domain.ModuleType.CONJUNCTION;
import static main.java.day20.domain.ModuleType.FLIP_FLOP;
import static main.java.day20.domain.PulseType.HIGH;
import static main.java.day20.domain.PulseType.LOW;

public class PulseProcessor {

    public static final String BROADCASTER_NAME = "broadcaster";
    public static final String EMPTY_STRING = "";
    private static final String RX_NAME = "rx";
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
            destinations.add(parts[i].replace(",", EMPTY_STRING));
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
        Counter counter = new Counter(new HashMap<>(Map.of(LOW.name(), 0L, HIGH.name(), 0L)));
        IntStream.range(0, numTimes).forEach(x -> pushButtonOnce(counter, x, null));
        return counter.score();
    }

    public long pushButtonForRx() {
        String modulePointingToRxName = modules.values().stream()
                .filter(module -> module.getDestinations().contains(RX_NAME))
                .map(Module::getName)
                .findFirst().orElseThrow();
        Counter modulePointingToRxInputsCounts = new Counter(modules.values().stream()
                .filter(module -> module.getDestinations().contains(modulePointingToRxName))
                .collect(Collectors.toMap(Module::getName, v -> 0L)));
        long count = 0L;

        while (modulePointingToRxInputsCounts.hasAnyInputNotTouched()) {
            count++;

            pushButtonOnce(modulePointingToRxInputsCounts, count, modulePointingToRxName);
        }
        return modulePointingToRxInputsCounts.getLCM();
    }

    private void pushButtonOnce(Counter counter, long pushCnt, String phase2ModName) {
        LinkedList<WorkItem> queue = new LinkedList<>(List.of(new WorkItem(BROADCASTER_NAME, LOW, EMPTY_STRING)));

        while (!queue.isEmpty()) {
            WorkItem work = queue.removeFirst();
            if (phase2ModName != null) {
                counter.countModulePointingToRx(work, pushCnt, phase2ModName);
            } else {
                counter.countPulse(work);
            }
            if (modules.containsKey(work.destination())) {
                Module module = modules.get(work.destination());
                module.input(work.source(), work.pulseType(), queue);
            }
        }
    }
}
