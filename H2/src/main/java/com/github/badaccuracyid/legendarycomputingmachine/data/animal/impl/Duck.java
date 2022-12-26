package com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;

import java.util.List;

public class Duck extends Animal {

    public Duck() {
        super(Habitat.LAND, 200, 280, 2);
    }

    @Override
    public List<String> getTypes() {
        return List.of("Bali Duck", "Campbell Duck");
    }

}
