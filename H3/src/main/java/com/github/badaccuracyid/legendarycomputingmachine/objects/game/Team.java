package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final String teamName, teamOwner;
    private final List<Player> playerList;
    private int money = 1000000;

    public Team(String teamName, String teamOwner) {
        this.teamName = teamName;
        this.teamOwner = teamOwner;
        this.playerList = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamOwner() {
        return teamOwner;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Player> getGoalKeepers() {
        List<Player> goalKeepers = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getPosition().toString().equals("GK")) {
                goalKeepers.add(player);
            }
        }

        return goalKeepers;
    }

    public List<Player> getDefenders() {
        List<Player> defenders = new ArrayList<>();
        List<PlayerPosition> positions = PlayerPosition.getPositionsByRole(PlayerRole.DEFENDER);

        for (Player player : playerList) {
            if (positions.contains(player.getPosition())) {
                defenders.add(player);
            }
        }

        return defenders;
    }

    public List<Player> getMidfielders() {
        List<Player> midfielders = new ArrayList<>();
        List<PlayerPosition> positions = PlayerPosition.getPositionsByRole(PlayerRole.MIDFIELDER);

        for (Player player : playerList) {
            if (positions.contains(player.getPosition())) {
                midfielders.add(player);
            }
        }

        return midfielders;
    }

    public List<Player> getAttackers() {
        List<Player> attackers = new ArrayList<>();
        List<PlayerPosition> positions = PlayerPosition.getPositionsByRole(PlayerRole.ATTACKER);

        for (Player player : playerList) {
            if (positions.contains(player.getPosition())) {
                attackers.add(player);
            }
        }

        return attackers;
    }
}
