package com.github.badaccuracyid.legendarycomputingmachine.game.tasks;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.Worker;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TickTask implements Runnable {

    private final Game game;

    public TickTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        AtomicInteger time = game.getTime();
        int get = time.incrementAndGet();
        if (get % 60 == 0) {
            game.onNextRound();
            return;
        }

        game.getWorkerList().forEach(Worker::tick);

        List<Customer> customerList = game.getCustomerList();
        customerList.removeIf(it -> {
            boolean ready = it.isDrinkPrepared() && it.isFoodPrepared();
            if (ready) {
                game.getScore().addAndGet(it.getTotalPrice());
            }

            return ready;
        });

        customerList.removeIf(it -> {
            boolean remove = it.getPatience() <= 0;
            if (remove) {
                game.getLife().addAndGet(-1);
            }

            return remove;
        });

        if (game.getLife().get() <= 0) {
            game.onLost();
            return;
        }

        customerList.forEach(it -> it.tick(100 - game.getRestaurantAttribute().getPatienceModifier()));
    }

}
