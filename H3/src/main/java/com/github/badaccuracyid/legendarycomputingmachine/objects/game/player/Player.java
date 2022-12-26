package com.github.badaccuracyid.legendarycomputingmachine.objects.game.player;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerPosition;

public abstract class Player {

    private final String shirtNumber, name;
    private final PlayerPosition position;
    private final int appearance;
    private final int age;
    private final String nationality;

    private Attribute attribute;

    public Player(String shirtNumber, String name, PlayerPosition position, int age, String nationality) {
        this.shirtNumber = shirtNumber;
        this.name = name;
        this.position = position;
        this.appearance = 0;
        this.age = age;
        this.nationality = nationality;
    }

    public String getShirtNumber() {
        return shirtNumber;
    }

    public String getName() {
        return name;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAppearance() {
        return appearance;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public abstract void setAttribute(int i1, int i2);

    public abstract double getOverallRating();

    public int getTransferFee() {
        return (int) (this.getOverallRating() * 1000);
    }
}
