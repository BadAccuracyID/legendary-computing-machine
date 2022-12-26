package com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;

import java.util.List;

public class Salmon extends Animal {

    public Salmon() {
        super(Habitat.SALT_WATER, 800, 1000, 2);
    }

    @Override
    public List<String> getTypes() {
        return List.of("Sockeye Salmon", "Chinook Salmon");
    }

}
