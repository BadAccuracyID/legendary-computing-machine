package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.objects.Food;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private final AtomicInteger patience = new AtomicInteger(0);
    private final Food order;

    public Customer(Main main) {
        // random 50-60
        patience.set((int) (Math.random() * 10) + 50);

        // get 1 random food
        List<Food> food = main.getDatabase().getFoodList();
        order = food.get((int) (Math.random() * food.size())).clone();
    }

    public void tick() {
        int get = patience.decrementAndGet();
        if (get <= 0) {
            patience.set(0);
        }
    }

    public int getPatience() {
        return patience.get();
    }

    public boolean isPatienceZero() {
        return patience.get() <= 0;
    }

    public Food getOrder() {
        return order;
    }

}
