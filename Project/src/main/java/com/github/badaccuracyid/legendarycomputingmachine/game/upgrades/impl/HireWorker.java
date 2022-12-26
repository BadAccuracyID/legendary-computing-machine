package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.Worker;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.RestaurantUpgrade;

import java.util.List;

public class HireWorker extends RestaurantUpgrade {

    public HireWorker() {
        super("Hire Worker", 40000, Integer.MAX_VALUE);
    }

    @Override
    public void applyEffect(Game game) {
        List<Worker> workerList = game.getWorkerList();
        workerList.add(new Worker(game));
    }
}
