package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;

public abstract class RestaurantUpgrade {

    private final String name;
    private final int initialValue;
    private final int maxUpgrades;

    private int upgrades = 0;

    public RestaurantUpgrade(String name, int initialValue, int maxUpgrades) {
        this.name = name;
        this.initialValue = initialValue;
        this.maxUpgrades = maxUpgrades;
    }

    public String getName() {
        return name;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getMaxUpgrades() {
        return maxUpgrades;
    }


    public int getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(int upgrades) {
        this.upgrades = upgrades;
    }

    public int getFinalCost() {
        return this.initialValue * (this.upgrades + 1);
    }

    public abstract void applyEffect(Game game);

}
