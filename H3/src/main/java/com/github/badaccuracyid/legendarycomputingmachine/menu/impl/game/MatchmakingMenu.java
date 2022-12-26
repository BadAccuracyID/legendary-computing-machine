package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

public class MatchmakingMenu extends Menu {

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Database database = main.getDatabase();
        if (database.getFirstTeam().getPlayerList().size() != 11) {
            System.out.println("The first team is not ready yet! Must be 11 players!");
            this.enterOrGoBack();
            return;
        }

        if (database.getFirstTeam().getGoalKeepers().size() != 1) {
            System.out.println("The first team is not ready yet! Must be 1 goalkeeper!");
            this.enterOrGoBack();
            return;
        }

        if (database.getFirstTeam().getDefenders().size() != 4) {
            System.out.println("The first team is not ready yet! Must be 4 defenders!");
            this.enterOrGoBack();
            return;
        }

        if (database.getFirstTeam().getMidfielders().size() != 3) {
            System.out.println("The first team is not ready yet! Must be 3 midfielders!");
            this.enterOrGoBack();
            return;
        }

        if (database.getFirstTeam().getAttackers().size() != 3) {
            System.out.println("The first team is not ready yet! Must be 3 attackers!");
            this.enterOrGoBack();
            return;
        }

        System.out.println("Starting Line Up");
        System.out.println("===========================");
        System.out.println();

        GameUtils.displayTeamMembers(database.getFirstTeam());
        System.out.println();
        System.out.println();
        GameUtils.displayTeamMembers(database.getEnemyTeam());

        System.out.println();
        System.out.println();
        System.out.println("Press enter to start the game!");
        main.getScanner().nextLine();
        System.out.println();

        // value = all players overall rating
        double teamValue = database.getFirstTeam().getPlayerList().stream().mapToDouble(Player::getOverallRating).sum() * 1000D;
        double enemyTeamValue = database.getEnemyTeam().getPlayerList().stream().mapToDouble(Player::getOverallRating).sum() * 1000D;

        // win percentage = team value / (team value + opponent value) * 100
        double winPercentage = teamValue / (teamValue + enemyTeamValue) * 100;

        int winningPrize;

        int teamScore;
        int enemyTeamScore;

        if (winPercentage > 50.0) {
            // win
            winningPrize = 20000;
            teamScore = (int) (Math.random() * 6) + 1;
            enemyTeamScore = (int) (Math.random() * (teamScore - 1));
        } else if (winPercentage > 48.0) {
            // draw
            winningPrize = 15000;
            teamScore = (int) (Math.random() * 6) + 1;
            enemyTeamScore = teamScore;
        } else {
            // lose
            winningPrize = 10000;
            enemyTeamScore = (int) (Math.random() * 6) + 1;
            teamScore = (int) (Math.random() * (enemyTeamScore - 1));
        }

        int teamFee = (int) (database.getFirstTeam().getPlayerList().stream().mapToDouble(Player::getOverallRating).sum() * 10);
        database.getFirstTeam().setMoney(database.getFirstTeam().getMoney() - teamFee + winningPrize);

        System.out.printf("| %-20s | %-20s |\n", "Your team value", "Opponent team value");
        System.out.printf("| %-20s | %-20s |\n", (int) teamValue, (int) enemyTeamValue);
        System.out.println();
        System.out.println("Final Score: " + teamScore + " - " + enemyTeamScore);
        if (teamScore > enemyTeamScore) {
            System.out.println("Your team won the game!");
        } else if (teamScore == enemyTeamScore) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Your team lost!");
        }
        System.out.println("Winning Prize: " + winningPrize);
        System.out.println("Team Fee: " + teamFee);
        System.out.println();

        this.enterOrGoBack();
    }
}
