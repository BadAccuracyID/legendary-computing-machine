package com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerPosition;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Attribute;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;

public class MidFielder extends Player {

    public MidFielder(String shirtNumber, String name, PlayerPosition position, int age, String nationality) {
        super(shirtNumber, name, position, age, nationality);

        // random 40 - 99
        int att1Value = (int) (Math.random() * 60) + 40;
        int att2Value = (int) (Math.random() * 60) + 40;

        this.setAttribute(att1Value, att2Value);
    }

    @Override
    public void setAttribute(int lowPass, int loftedPass) {
        Attribute attribute = new Attribute("Low Pass", "Lofted Pass", lowPass, loftedPass);
        this.setAttribute(attribute);
    }

    @Override
    public double getOverallRating() {
        Attribute attribute = this.getAttribute();
        return (double) attribute.getAtt1Value() * 0.5 + (double) attribute.getAtt2Value() * 0.5;
    }
}
