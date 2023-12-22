package main.resources.day20.domain;

import java.util.List;

public abstract class Module {
    private final String name;
    private final List<String> destinations;

    public Module(String name, List<String> destinations) {
        this.name = name;
        this.destinations = destinations;
    }

    public String getName() {
        return name;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    protected final void send(PulseType pulseType, List<WorkItem> queue) {
        for (String destination : destinations) {
            queue.add(new WorkItem(destination, pulseType, this.name));
        }
    }

    public abstract void input(String source, PulseType pulseType, List<WorkItem> queue);
}
