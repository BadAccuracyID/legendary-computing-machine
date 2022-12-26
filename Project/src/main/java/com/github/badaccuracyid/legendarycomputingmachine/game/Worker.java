package com.github.badaccuracyid.legendarycomputingmachine.game;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Consumable;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker {

    private final Game game;
    private final AtomicInteger progress;

    private Customer customer = null;
    private Consumable consumable = null;

    public Worker(Game game) {
        this.game = game;
        progress = new AtomicInteger(0);
    }

    public String getProgress() {
        StringBuilder progressBar = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            if (i < progress.get()) {
                progressBar.append("█");
            } else {
                progressBar.append("░");
            }
        }

        return progressBar.toString();
    }

    public void tick() {
        if (consumable == null) {
            this.findToCook();
            return;
        }

        if (!game.getCustomerList().contains(customer)) {
            this.reset();
            return;
        }

        if (customer.isFoodPrepared()) {
            if (this.consumable.equals(customer.getFood())) {
                this.reset();
                return;
            }
        }

        int get = progress.incrementAndGet();
        if (get >= 10) {
            customer.onOrderPrepare(consumable.getName());
            this.reset();
        }
    }

    private void findToCook() {
        List<Customer> customerList = game.getCustomerList();
        if (customerList.isEmpty()) {
            return;
        }

        for (Customer customer : customerList) {
            if (game.getWorkerList().stream().anyMatch(it -> it.getCustomer() == customer)) {
                continue;
            }
            if (customer.isFoodPrepared()) {
                continue;
            }

            consumable = customer.getFood();
            this.customer = customer;
        }
    }

    private Customer getCustomer() {
        return customer;
    }

    private void reset() {
        progress.set(0);
        consumable = null;
        customer = null;
    }
}
