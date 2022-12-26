package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.RestaurantUpgrade;

public class BuyDecorations extends RestaurantUpgrade {

    public BuyDecorations() {
        super("Buy Decorations", 35000, 20);
    }

    @Override
    public void applyEffect(Game game) {
        game.getRestaurantAttribute().setPatienceModifier(this.getUpgrades() * 5);
    }
}
