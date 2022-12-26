package com.github.badaccuracyid.legendarycomputingmachine.data;

import java.util.Iterator;
import java.util.List;

public enum Habitat {

    LAND("Land", List.of("Cow", "Duck")),
    WATER("Water", List.of("Catfish", "Salmon")),
    FRESH_WATER("Fresh Water", List.of("Catfish", "Salmon")),
    SALT_WATER("Salt Water", List.of("Catfish", "Salmon"));

    private final String name;
    private final List<String> animals;

    Habitat(String name, List<String> animals) {
        this.name = name;
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public Iterator<String> getAnimals() {
        return animals.iterator();
    }

    public boolean isValidAnimal(String animal) {
        return animals.contains(animal);
    }
}
