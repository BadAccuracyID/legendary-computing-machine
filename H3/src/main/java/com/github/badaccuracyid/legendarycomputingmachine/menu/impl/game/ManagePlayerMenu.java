package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.GameUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;

public class ManagePlayerMenu extends Menu {

    @Override
    protected void showMenu() {
        int choice = InputUtils.determineIntChoice.apply(() -> {
            Utils.clearScreen();
            Utils.printLocalLogo();

            System.out.println("Team Name: " + main.getDatabase().getFirstTeam().getTeamName());
            System.out.println("Team Owner: " + main.getDatabase().getFirstTeam().getTeamOwner());
            System.out.println("Team Money: " + main.getDatabase().getFirstTeam().getMoney());
            System.out.println();

            System.out.println("1. View All Players");
            System.out.println("2. View First Team");
            System.out.println("3. Add Player to First Team");
            System.out.println("4. Remove Player from First Team");
            System.out.println("5. Upgrade Player Skill");
            System.out.println("6. Exit");
            System.out.print(">> ");
        }, Arrays.asList(
                new IntRequirement(
                        input -> input > 0,
                        "[!] Input must be greater than 0!"
                ), new IntRequirement(
                        input -> input < 7,
                        "[!] Input must be less than 7!"
                )));

        switch (choice) {
            case 1:
                Utils.clearScreen();
                GameUtils.displayTeamMembers(main.getDatabase().getFirstTeam());
                System.out.println();
                System.out.println();
                GameUtils.displayTeamMembers(main.getDatabase().getBackupTeam());
                System.out.println();
                this.enterOrGoBack();
                break;
            case 2:
                GameUtils.displayTeamMembers(main.getDatabase().getFirstTeam());
                this.enterOrGoBack();
                break;
            case 3:
                this.open(AddPlayerToFirstTeamMenu.class);
                break;
            case 4:
                this.open(RemovePlayerFromFirstTeamMenu.class);
                break;
            case 5:
                this.open(UpgradePlayerSkillMenu.class);
                break;
            case 6:
                this.open(GameMenu.class);
                break;
        }
    }
}
