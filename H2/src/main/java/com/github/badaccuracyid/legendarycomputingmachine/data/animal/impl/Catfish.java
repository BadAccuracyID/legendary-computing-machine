package com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;

import java.util.List;

public class Catfish extends Animal {

    public Catfish() {
        super(Habitat.FRESH_WATER, 100, 120, 2);
    }

    @Override
    public List<String> getTypes() {
        return List.of("Channel Catfish", "Blue Catfish");
    }

}
