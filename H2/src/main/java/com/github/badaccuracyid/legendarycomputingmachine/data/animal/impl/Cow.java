package com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;

import java.util.List;

public class Cow extends Animal {

    public Cow() {
        super(Habitat.LAND, 400, 500, 4);
    }

    @Override
    public List<String> getTypes() {
        return List.of("Angus Cow", "Hereford Cow");
    }
}
