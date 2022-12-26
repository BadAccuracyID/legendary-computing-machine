package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Attribute;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;
import java.util.Optional;

public class UpgradePlayerSkillMenu extends Menu {

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Database database = main.getDatabase();
        GameUtils.printBothTeams(database.getFirstTeam(), database.getBackupTeam());
        System.out.println();

        System.out.println("Team Money: " + database.getFirstTeam().getMoney());
        System.out.println("Upgrade price: 10000");

        int choice = InputUtils.determineIntChoice.apply(() -> {

            System.out.print("Select player shirt number to add to the first team [0 for exit]: ");
        }, Arrays.asList(
                new IntRequirement(
                        input -> input > -1,
                        "[!] Input must be greater than -1!"
                ), new IntRequirement(
                        input -> input < 100,
                        "[!] Input must be less than 100!"
                ), new IntRequirement(
                        input -> {
                            if (input == 0) {
                                return true;
                            } else {
                                Optional<Player> player = database.getFirstTeam().getPlayerList().stream().filter(player1 -> Integer.parseInt(player1.getShirtNumber()) == input).findFirst();
                                Optional<Player> player2 = database.getBackupTeam().getPlayerList().stream().filter(player1 -> Integer.parseInt(player1.getShirtNumber()) == input).findFirst();
                                return player.isPresent() || player2.isPresent();
                            }
                        },
                        "[!] Player with that shirt number does not exist!"
                )));

        if (choice == 0) {
            this.open(ManagePlayerMenu.class);
            return;
        }

        if (database.getFirstTeam().getMoney() < 10000) {
            System.out.println("Not enough money!");
            this.enterOrGoBack();
            return;
        }

        Player player;
        Optional<Player> playerOptional = database.getFirstTeam().getPlayerList().stream().filter(it -> Integer.parseInt(it.getShirtNumber()) == choice).findFirst();
        if (playerOptional.isPresent()) {
            player = playerOptional.get();
        } else {
            playerOptional = database.getBackupTeam().getPlayerList().stream().filter(player1 -> Integer.parseInt(player1.getShirtNumber()) == choice).findFirst();
            if (playerOptional.isPresent()) {
                player = playerOptional.get();
            } else {
                this.open(ManagePlayerMenu.class);
                return;
            }
        }

        Attribute attribute = player.getAttribute();
        int oldAtt1 = attribute.getAtt1Value();
        int oldAtt2 = attribute.getAtt2Value();
        if (oldAtt1 > 94 || oldAtt2 > 94) {
            // too high
            System.out.println("[!] Player has already reached maximum in one of their skills!");
            this.enterOrGoBack();
            return;
        }

        attribute.setAtt1Value(oldAtt1 + 3);
        attribute.setAtt2Value(oldAtt2 + 3);
        database.getFirstTeam().setMoney(database.getFirstTeam().getMoney() - 10000);

        System.out.println();
        System.out.println("Player " + player.getName() + " has been upgraded!");
        System.out.println(attribute.getAtt1Name() + ": " + oldAtt1 + " -> " + attribute.getAtt1Value());
        System.out.println(attribute.getAtt2Name() + ": " + oldAtt2 + " -> " + attribute.getAtt2Value());
        System.out.println();

        this.enterOrGoBack();
    }
}
