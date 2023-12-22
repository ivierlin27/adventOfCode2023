package main.resources.day20.domain;

import java.util.List;

public class Broadcast extends Module {
    public Broadcast(String name, List<String> destinations) {
        super(name, destinations);
    }

    @Override
    public void input(String source, PulseType pulseType, List<WorkItem> queue) {
        send(pulseType, queue);
    }
}
