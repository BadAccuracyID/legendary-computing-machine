package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Team;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;

import java.util.List;

public class Database {

    private Team firstTeam, backupTeam;
    private Team enemyTeam;

    public Team getFirstTeam() {
        return firstTeam;
    }

    public Team getBackupTeam() {
        return backupTeam;
    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void startGame() {
        firstTeam.getPlayerList().clear();
        backupTeam = new Team(firstTeam.getTeamName() + " - 2", firstTeam.getTeamOwner());

        List<Player> playerList = GameUtils.generatePlayersForTwo();
        backupTeam.getPlayerList().addAll(playerList);

        enemyTeam = new Team("Evil Incorporated", "Heinz Doofenshmirtz");
        List<Player> enemyPlayerList = GameUtils.generatePlayers();
        enemyTeam.getPlayerList().addAll(enemyPlayerList);
    }

    public void regenerateEnemyTeam() {
        List<Player> playerList = GameUtils.generatePlayers();
        enemyTeam.getPlayerList().clear();
        enemyTeam.getPlayerList().addAll(playerList);
    }

    public void resetGame() {
        firstTeam = null;
        backupTeam = null;
        enemyTeam = null;
    }
}
