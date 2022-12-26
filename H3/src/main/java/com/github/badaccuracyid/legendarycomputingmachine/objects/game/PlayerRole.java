package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

public enum PlayerRole {
    GOALKEEPER("Goalkeeper"),
    DEFENDER("Defender"),
    MIDFIELDER("Midfielder"),
    ATTACKER("Attacker");

    private final String name;

    PlayerRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
