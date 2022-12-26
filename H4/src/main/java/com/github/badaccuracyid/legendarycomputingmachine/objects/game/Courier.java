package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import com.github.badaccuracyid.legendarycomputingmachine.objects.Food;

import java.util.concurrent.atomic.AtomicInteger;

public class Courier {

    private final AtomicInteger distance = new AtomicInteger(0);
    private final AtomicInteger wait = new AtomicInteger(2);
    private final Food food;

    public Courier(Food food) {
        this.food = food;
    }

    public void tick() {
        if (wait.get() > 0) {
            int get = wait.decrementAndGet();
            if (get <= 0) {
                wait.set(0);
            }
        } else {
            int get = distance.decrementAndGet();
            if (get <= 0) {
                distance.set(0);
            }
        }
    }

    public boolean doIt() {
        return wait.get() <= 0;
    }

    public int getDistance() {
        return distance.get();
    }

    public int getWait() {
        return wait.get();
    }

    public boolean isCollecting() {
        return wait.get() > 0;
    }

    public boolean isDelivered() {
        return distance.get() <= 0 && wait.get() <= 0;
    }

    public Food getFood() {
        return food;
    }
}
