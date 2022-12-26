package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        int choice = InputUtils.determineIntChoice.apply(
                () -> {
                    Utils.clearScreen();
                    Utils.printLocalLogo();

                    UserData currentUser = main.getDatabase().getCurrentUser();
                    System.out.println("╔══════════════════════╤═════════════════════╗");
                    System.out.printf("║ %-20s │ Score: %-12d ║\n", currentUser.getUsername(), currentUser.getHighScore());
                    System.out.println("╚══════════════════════╧═════════════════════╝");

                    System.out.println();
                    System.out.println("1. Play game");
                    System.out.println("2. View scoreboard");
                    System.out.println("3. Exit");
                    System.out.print(">> ");
                }, List.of(new IntRequirement(
                        input -> input >= 1 && input <= 3,
                        "[!] Invalid choice, please try again."
                ))
        );

        switch (choice) {
            case 1:
                Game game = new Game(main);
                game.start();
                break;
            case 2:
                this.open(ScoreboardMenu.class);
                break;
            case 3:
                main.getDatabase().setCurrentUser(null);
                this.open(HomeMenu.class);
                break;
        }
    }
}
