package com.github.badaccuracyid.legendarycomputingmachine.data;

public enum Size {
    SMALL("Small", 50, 70),
    MEDIUM("Medium", 300, 350),
    LARGE("Large", 650, 800);

    private final String name;
    private final int price, sellPrice;

    Size(String name, int price, int sellPrice) {
        this.name = name;
        this.price = price;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
