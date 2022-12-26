package com.github.badaccuracyid.legendarycomputingmachine.menu.impl.game;

import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.StrRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.PlayerPosition;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Team;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Attribute;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.Player;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Attacker;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Defender;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.Goalkeeper;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.player.impl.MidFielder;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AddPlayerMenu extends Menu {

    @Override
    protected void showMenu() {
        Utils.clearScreen();

        Team currentTeam = main.getDatabase().getFirstTeam();
        int shirtNumber = InputUtils.determineIntChoice.apply(
                () -> {
                    System.out.print("Enter shirt number [1 .. 99 | unique]: ");
                }, Arrays.asList(
                        new IntRequirement(
                                input -> input > 0,
                                "[!] Input must be greater than 0!"
                        ), new IntRequirement(
                                input -> input < 100,
                                "[!] Input must be less than 100!"
                        ), new IntRequirement(
                                input -> currentTeam.getPlayerList().stream().noneMatch(player -> Integer.parseInt(player.getShirtNumber()) == input),
                                "[!] Shirt number must be unique!"
                        )
                ));

        String playerName = InputUtils.determineStrChoice.apply(
                () -> {
                    System.out.print("Enter player name [3 .. 20 characters]: ");
                }, Arrays.asList(
                        new StrRequirement(
                                input -> input.length() > 3,
                                "[!] PlayerName must be greater than 3 characters!"
                        ), new StrRequirement(
                                input -> input.length() < 20,
                                "[!] PlayerName must be less than 20 characters!"
                        )
                ));

        String playerPositionString = InputUtils.determineStrChoice.apply(
                () -> {
                    System.out.print("Enter player position [");
                    Iterator<PlayerPosition> positionIterator = Arrays.stream(PlayerPosition.values()).iterator();
                    while (positionIterator.hasNext()) {
                        PlayerPosition position = positionIterator.next();
                        System.out.print(position.name());
                        if (positionIterator.hasNext()) {
                            System.out.print(" | ");
                        }
                    }
                    System.out.print("]: ");
                }, List.of(
                        new StrRequirement(
                                input -> Arrays.stream(PlayerPosition.values()).anyMatch(playerPosition -> playerPosition.toString().equals(input)),
                                "[!] Player position must be valid!"
                        )
                ));
        PlayerPosition playerPosition = PlayerPosition.valueOf(playerPositionString);

        int playerAge = InputUtils.determineIntChoice.apply(
                () -> {
                    System.out.print("Enter player age [15 .. 45]: ");
                }, Arrays.asList(
                        new IntRequirement(
                                input -> input >= 15,
                                "[!] Player age must be equal or greater than 15!"
                        ), new IntRequirement(
                                input -> input <= 45,
                                "[!] Player age must be less than or equal to 45!"
                        )
                ));

        String playerNationality = InputUtils.determineStrChoice.apply(
                () -> {
                    System.out.print("Enter player nationality [3 .. 20 characters]: ");
                }, Arrays.asList(
                        new StrRequirement(
                                input -> input.length() > 3,
                                "[!] PlayerNationality must be greater than 3 characters!"
                        ), new StrRequirement(
                                input -> input.length() < 20,
                                "[!] PlayerNationality must be less than 20 characters!"
                        )
                ));

        Player player;
        switch (playerPosition.getRole()) {
            case GOALKEEPER:
                player = new Goalkeeper(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                break;
            case DEFENDER:
                player = new Defender(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                break;
            case MIDFIELDER:
                player = new MidFielder(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                break;
            case ATTACKER:
                player = new Attacker(String.valueOf(shirtNumber), playerName, playerPosition, playerAge, playerNationality);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + playerPosition.getRole());
        }

        Utils.clearScreen();

        // print player stats
        Attribute attribute = player.getAttribute();
        System.out.printf("%-20s: %s%n", attribute.getAtt1Name(), attribute.getAtt1Value());
        System.out.printf("%-20s: %s%n", attribute.getAtt2Name(), attribute.getAtt2Value());
        System.out.printf("%-20s: %s%n", "Shirt Number", shirtNumber);
        System.out.printf("%-20s: %s%n", "Player Name", playerName);
        System.out.printf("%-20s: %s%n", "Appearance", player.getAppearance());
        System.out.printf("%-20s: %s%n", "Player Position", playerPosition);
        System.out.printf("%-20s: %s%n", "Age", playerAge);
        System.out.printf("%-20s: %s%n", "Nationality", playerNationality);
        System.out.println();

        System.out.println("Player transfer fee: " + player.getTransferFee());
        System.out.println("Team money: " + currentTeam.getMoney());
        System.out.println();

        System.out.print("Do you want to add this player? [y/n]: ");
        String choice = InputUtils.determineStrChoice.apply(
                () -> {
                }, Arrays.asList(
                        new StrRequirement(
                                input -> input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"),
                                "[!] Input must be y or n!"
                        ),
                        new StrRequirement(
                                input -> input.equalsIgnoreCase("y") && player.getTransferFee() <= currentTeam.getMoney(),
                                "[!] Team doesn't have enough money!"
                        )
                ));

        if (choice.equalsIgnoreCase("y")) {
            main.getDatabase().getFirstTeam().getPlayerList().add(player);
            currentTeam.getPlayerList().add(player);
            currentTeam.setMoney(currentTeam.getMoney() - player.getTransferFee());
            System.out.println("Player transfer finished!");
        } else {
            System.out.println("Player transfer cancelled!");
        }

        System.out.println();
        this.enterOrGoBack();
    }
}
