package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;
import java.util.Optional;

public class RemovePlayerFromFirstTeamMenu extends Menu {

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Database database = main.getDatabase();
        GameUtils.printBothTeams(database.getFirstTeam(), database.getBackupTeam());
        System.out.println();

        int choice = InputUtils.determineIntChoice.apply(() -> {
            System.out.print("Select player shirt number to remove from the first team [0 for exit]: ");
        }, Arrays.asList(
                new IntRequirement(
                        input -> input > -1,
                        "[!] Input must be greater than -1!"
                ), new IntRequirement(
                        input -> input < 100,
                        "[!] Input must be less than 100!"
                ), new IntRequirement(
                        input -> database.getFirstTeam().getPlayerList().stream().anyMatch(player -> Integer.parseInt(player.getShirtNumber()) == input),
                        "[!] Player with that shirt number does not exist!"
                )));

        if (choice == 0) {
            this.open(ManagePlayerMenu.class);
            return;
        }

        Optional<Player> playerOptional = database.getFirstTeam().getPlayerList().stream().filter(player -> Integer.parseInt(player.getShirtNumber()) == choice).findFirst();
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            database.getFirstTeam().getPlayerList().remove(player);
            database.getBackupTeam().getPlayerList().add(player);
            System.out.println("[!] Player removed from first team!");
        } else {
            System.out.println("[!] Player with that shirt number does not exist!");
        }

        System.out.println();
        System.out.println("Press enter to continue...");
        main.getScanner().nextLine();
        this.open(RemovePlayerFromFirstTeamMenu.class);
    }

}
