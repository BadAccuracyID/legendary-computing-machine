package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.RestaurantAttribute;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.RestaurantUpgrade;

public class ExpandRestaurant extends RestaurantUpgrade {

    public ExpandRestaurant() {
        super("Expand Restaurant", 28500, Integer.MAX_VALUE);
    }

    @Override
    public void applyEffect(Game game) {
        RestaurantAttribute attribute = game.getRestaurantAttribute();
        attribute.setMaxCustomers(attribute.getMaxCustomers() + 1);
    }
}
