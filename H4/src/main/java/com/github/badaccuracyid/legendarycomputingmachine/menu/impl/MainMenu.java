package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.StrRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        String choice = InputUtils.determineStrChoice.apply(
                () -> {
                    Utils.clearScreen();
                    Utils.printLocalLogo();

                    System.out.println();
                    System.out.print("[S] Start Game        [Q] Quit\n\n");
                    System.out.print(">> ");
                },
                List.of(
                        new StrRequirement(
                                input -> input.equalsIgnoreCase("s") || input.equalsIgnoreCase("q"),
                                "[!] Invalid choice, please try again."
                        )
                ));

        switch (choice.toUpperCase()) {
            case "S":
                new Game(main).start();
                break;
            case "Q":
                System.exit(0);
                break;
        }
    }
}
