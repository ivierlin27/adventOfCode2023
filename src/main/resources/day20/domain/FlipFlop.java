package main.resources.day20.domain;

import java.util.List;

public class FlipFlop extends Module {
    boolean on = false;
    public FlipFlop(String name, List<String> destinations) {
        super(name, destinations);
    }

    @Override
    public void input(String source, PulseType pulseType, List<WorkItem> queue) {
        if (pulseType.equals(PulseType.LOW)) {
            on = !on;
            send(on ? PulseType.HIGH : PulseType.LOW, queue);
        }
    }
}
