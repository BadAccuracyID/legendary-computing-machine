package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.MainMenu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;

public class GameMenu extends Menu {

    @Override
    protected void showMenu() {
        int choice = InputUtils.determineIntChoice.apply(
                () -> {
                    Utils.clearScreen();
                    Utils.printLocalLogo();

                    System.out.println("Team Name: " + main.getDatabase().getFirstTeam().getTeamName());
                    System.out.println("Team Owner: " + main.getDatabase().getFirstTeam().getTeamOwner());
                    System.out.println("Team Money: " + main.getDatabase().getFirstTeam().getMoney());
                    System.out.println();

                    System.out.println("1. Add Player");
                    System.out.println("2. Manage Player");
                    System.out.println("3. Matchmaking");
                    System.out.println("4. Exit");
                    System.out.print(">> ");
                },
                Arrays.asList(
                        new IntRequirement(
                                input -> input > 0,
                                "[!] Input must be greater than 0!"
                        ), new IntRequirement(
                                input -> input < 5,
                                "[!] Input must be less than 5!"
                        )));

        switch (choice) {
            case 1:
                this.open(AddPlayerMenu.class);
                break;
            case 2:
                this.open(ManagePlayerMenu.class);
                break;
            case 3:
                main.getDatabase().regenerateEnemyTeam();
                this.open(MatchmakingMenu.class);
                break;
            case 4:
                main.getDatabase().setFirstTeam(null);
                main.getDatabase().resetGame();
                this.open(MainMenu.class);
                break;
        }
    }

}
