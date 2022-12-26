package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PlayerPosition {
    GK(PlayerRole.GOALKEEPER),
    CB(PlayerRole.DEFENDER),
    LB(PlayerRole.DEFENDER),
    RB(PlayerRole.DEFENDER),
    DMF(PlayerRole.MIDFIELDER),
    CMF(PlayerRole.MIDFIELDER),
    AMF(PlayerRole.MIDFIELDER),
    LWF(PlayerRole.ATTACKER),
    RWF(PlayerRole.ATTACKER),
    SS(PlayerRole.ATTACKER),
    CF(PlayerRole.ATTACKER);

    private final PlayerRole role;

    PlayerPosition(PlayerRole role) {
        this.role = role;
    }

    public PlayerRole getRole() {
        return role;
    }

    public static List<PlayerPosition> getPositionsByRole(PlayerRole role) {
        return Arrays.stream(PlayerPosition.values())
                .filter(position -> position.getRole() == role)
                .collect(Collectors.toList());
    }
}
