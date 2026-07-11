package com.seosean.zombieshologramsbugfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class DelayedTask {
    private static final List<Scheduled> TASKS = new ArrayList<>();

    private DelayedTask() {}

    public static void runLater(int delay, Runnable action) {
        TASKS.add(new Scheduled(delay, action));
    }

    static void tick() {
        Iterator<Scheduled> iterator = TASKS.iterator();
        while (iterator.hasNext()) {
            Scheduled task = iterator.next();
            if (--task.delay <= 0) {
                iterator.remove();
                task.action.run();
            }
        }
    }

    private static final class Scheduled {
        private int delay;
        private final Runnable action;
        private Scheduled(int delay, Runnable action) { this.delay = delay; this.action = action; }
    }
}
