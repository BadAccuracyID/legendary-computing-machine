package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerPosition;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerRole;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddPlayerToFirstTeamMenu extends Menu {

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Database database = main.getDatabase();
        GameUtils.printBothTeams(database.getFirstTeam(), database.getBackupTeam());
        System.out.println();

        int choice = InputUtils.determineIntChoice.apply(() -> {
            System.out.print("Select player shirt number to add to the first team [0 for exit | 111 for auto]: ");
        }, Arrays.asList(
                new IntRequirement(
                        input -> input > -1,
                        "[!] Input must be greater than -1!"
                ), new IntRequirement(
                        input -> input < 112,
                        "[!] Input must be less than 112!"
                ), new IntRequirement(
                        input -> {
                            if (input == 0) {
                                return true;
                            } else if (input == 111) {
                                return true;
                            } else {
                                Optional<Player> player = database.getBackupTeam().getPlayerList().stream().filter(player1 -> Integer.parseInt(player1.getShirtNumber()) == input).findFirst();
                                return player.isPresent();
                            }
                        },
                        "[!] Player with that shirt number does not exist!"
                )));

        if (choice == 0) {
            this.open(ManagePlayerMenu.class);
            return;
        }

        if (choice == 111) {
            int req = 1 - database.getFirstTeam().getGoalKeepers().size();
            if (req > 0) {
                for (int i = 0; i < req; i++) {
                    Optional<Player> playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player -> player.getPosition().equals(PlayerPosition.GK)).findFirst();
                    if (playerOptional.isPresent()) {
                        Player player = playerOptional.get();
                        database.getFirstTeam().getPlayerList().add(player);
                        database.getBackupTeam().getPlayerList().remove(player);
                    }
                }
            }

            req = 4 - database.getFirstTeam().getDefenders().size();
            if (req > 0) {
                List<PlayerPosition> defenders = PlayerPosition.getPositionsByRole(PlayerRole.DEFENDER);
                for (int i = 0; i < req; i++) {
                    Optional<Player> playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player -> defenders.contains(player.getPosition())).findFirst();
                    if (playerOptional.isPresent()) {
                        Player player = playerOptional.get();
                        database.getFirstTeam().getPlayerList().add(player);
                        database.getBackupTeam().getPlayerList().remove(player);
                    }
                }
            }

            req = 3 - database.getFirstTeam().getMidfielders().size();
            if (req > 0) {
                List<PlayerPosition> midfielders = PlayerPosition.getPositionsByRole(PlayerRole.MIDFIELDER);
                for (int i = 0; i < req; i++) {
                    Optional<Player> playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player -> midfielders.contains(player.getPosition())).findFirst();
                    if (playerOptional.isPresent()) {
                        Player player = playerOptional.get();
                        database.getFirstTeam().getPlayerList().add(player);
                        database.getBackupTeam().getPlayerList().remove(player);
                    }
                }
            }

            req = 3 - database.getFirstTeam().getAttackers().size();
            if (req > 0) {
                List<PlayerPosition> attackers = PlayerPosition.getPositionsByRole(PlayerRole.ATTACKER);
                for (int i = 0; i < req; i++) {
                    Optional<Player> playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player -> attackers.contains(player.getPosition())).findFirst();
                    if (playerOptional.isPresent()) {
                        Player player = playerOptional.get();
                        database.getFirstTeam().getPlayerList().add(player);
                        database.getBackupTeam().getPlayerList().remove(player);
                    }
                }
            }

            this.open(AddPlayerToFirstTeamMenu.class);
            return;
        }

        Optional<Player> playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player -> Integer.parseInt(player.getShirtNumber()) == choice).findFirst();
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            database.getBackupTeam().getPlayerList().remove(player);
            database.getFirstTeam().getPlayerList().add(player);
            System.out.println("[!] Player added to first team!");
        } else {
            System.out.println("[!] Player with that shirt number does not exist!");
        }

        System.out.println();
        System.out.println("Press enter to continue...");
        main.getScanner().nextLine();
        this.open(AddPlayerToFirstTeamMenu.class);
    }

}
