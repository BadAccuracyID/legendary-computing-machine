package com.github.badaccuracyid.legendarycomputingmachine.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ExecutorManager {

    private final Map<String, LCMExecutor> executorMap = Collections.synchronizedMap(new HashMap<>());

    public LCMExecutor createOrGetSingleThreadScheduledExecutor(String name) {
        if (executorMap.containsKey(name)) {
            return executorMap.get(name);
        }

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        LCMExecutor neoCoreExecutor = new LCMExecutor() {
            @Override
            public void execute(Runnable command) {
                executor.execute(command);
            }

            @Override
            public void shutdownNow() {
                executor.shutdownNow();
            }

            @Override
            public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
                return executor.scheduleAtFixedRate(command, initialDelay, period, unit);
            }
        };

        executorMap.put(name, neoCoreExecutor);
        return neoCoreExecutor;
    }

    public void hardShutdown() {
        executorMap.values().forEach(LCMExecutor::shutdownNow);
        executorMap.clear();
    }

    public interface LCMExecutor extends Executor {

        void shutdownNow();

        ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

    }
}
