package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import java.util.Objects;

public abstract class Consumable {

    private final String name;
    private final int price;

    protected Consumable(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public boolean isFood() {
        return this instanceof Food;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumable that = (Consumable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
