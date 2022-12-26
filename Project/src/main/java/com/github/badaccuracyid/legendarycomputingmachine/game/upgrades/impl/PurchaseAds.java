package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.RestaurantUpgrade;

public class PurchaseAds extends RestaurantUpgrade {

    public PurchaseAds() {
        super("Purchase Ads", 37500, 20);
    }

    @Override
    public void applyEffect(Game game) {
        game.getRestaurantAttribute().setCustomerAttractionModifier(this.getUpgrades() * 5);
    }
}
