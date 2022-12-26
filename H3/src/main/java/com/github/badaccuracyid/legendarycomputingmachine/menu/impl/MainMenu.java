package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game.*;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;

public class MainMenu extends Menu {

    public MainMenu() {
        super();

        new CreateTeamMenu();

        new GameMenu();
        new AddPlayerMenu();

        new ManagePlayerMenu();
        new AddPlayerToFirstTeamMenu();
        new RemovePlayerFromFirstTeamMenu();
        new UpgradePlayerSkillMenu();

        new MatchmakingMenu();
    }

    @Override
    protected void showMenu() {
        int choice = InputUtils.determineIntChoice.apply(
                () -> {
                    Utils.clearScreen();
                    Utils.printLocalLogo();

                    System.out.println("1. Create Team");
                    System.out.println("2. Exit");
                    System.out.print(">> ");
                },
                Arrays.asList(
                        new IntRequirement(
                                input -> input > 0,
                                "[!] Input must be greater than 0!"
                        ), new IntRequirement(
                                input -> input < 3,
                                "[!] Input must be less than 3!"
                        )));

        switch (choice) {
            case 1:
                this.open(CreateTeamMenu.class);
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
