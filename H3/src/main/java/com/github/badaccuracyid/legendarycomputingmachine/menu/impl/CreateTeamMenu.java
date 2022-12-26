package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game.GameMenu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.StrRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Team;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;

public class CreateTeamMenu extends Menu {

    public CreateTeamMenu() {
        super();
    }

    @Override
    protected void showMenu() {
        Utils.clearScreen();

        String teamName = InputUtils.determineStrChoice.apply(
                () -> {
                    System.out.print("Enter team name [5 .. 20 characters]: ");
                },
                Arrays.asList(
                        new StrRequirement(
                                input -> input.length() > 5,
                                "[!] Team name must be longer than 5 characters!"
                        ), new StrRequirement(
                                input -> input.length() < 20,
                                "[!] Team name must be shorter than 20 characters!"
                        )));
        String ownerName = InputUtils.determineStrChoice.apply(
                () -> {
                    System.out.print("Enter owner name [5 .. 20 characters]: ");
                },
                Arrays.asList(
                        new StrRequirement(
                                input -> input.length() > 5,
                                "[!] Owner name must be longer than 5 characters!"
                        ), new StrRequirement(
                                input -> input.length() < 20,
                                "[!] Owner name must be shorter than 20 characters!"
                        )));

        Team team = new Team(teamName, ownerName);
        main.getDatabase().setFirstTeam(team);
        main.getDatabase().startGame();
        this.open(GameMenu.class);
    }
}
