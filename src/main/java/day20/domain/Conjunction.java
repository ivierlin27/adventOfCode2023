package main.java.day20.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.day20.domain.PulseType.HIGH;
import static main.java.day20.domain.PulseType.LOW;

public class Conjunction extends Module {
    private Map<String, PulseType> memory = new HashMap<>();
    public Conjunction(String name, List<String> destinations) {
        super(name, destinations);
    }

    public void addMemory(Map.Entry<String, PulseType> memory) {
        this.memory.put(memory.getKey(), memory.getValue());
    }

    @Override
    public void input(String source, PulseType pulseType, List<WorkItem> queue) {
        memory.put(source, pulseType);
        PulseType pulseToSend = memory.values().stream().allMatch(v -> v.equals(HIGH)) ? LOW : HIGH;
        send(pulseToSend, queue);
    }
}
