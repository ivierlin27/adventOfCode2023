package main.resources.day20.domain;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.Map;

import static main.resources.day20.domain.PulseType.HIGH;
import static main.resources.day20.domain.PulseType.LOW;

public final class Counter {
    Map<String, Long> cnt;

    public Counter(Map<String, Long> cnt) {
        this.cnt = cnt;
    }

    public void countPulse(WorkItem work) {
        if (work.pulseType().equals(LOW)) {
            incrementValue(LOW.name());
        } else if (work.pulseType().equals(HIGH)) {
            incrementValue(HIGH.name());
        }
    }

    public void countModulePointingToRx(WorkItem work, long pushCnt, String modulePointingToRxName) {
        if (work.destination().equals(modulePointingToRxName) && work.pulseType().equals(HIGH)) {
            cnt.put(work.source(), pushCnt);
        }
    }

    public long score() {
        return cnt.get(LOW.name()) * cnt.get(HIGH.name());
    }

    public long getLCM() {
        return cnt.values().stream().reduce(1L, ArithmeticUtils::lcm);
    }

    public boolean hasAnyInputNotTouched() {
        return cnt.values().stream().anyMatch(v -> v == 0L);
    }

    private void incrementValue(String key) {
        if (cnt.containsKey(key)) {
            cnt.put(key, cnt.get(key) + 1L);
        } else {
            cnt.put(key, 1L);
        }
    }
}
