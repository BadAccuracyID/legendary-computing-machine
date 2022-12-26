package com.github.badaccuracyid.legendarycomputingmachine.game.upgrades;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class UpgradeInterface {

    private final Game game;

    public UpgradeInterface(Game game) {
        this.game = game;
    }

    public void open() {
        int choice = InputUtils.determineIntChoice.apply(
                () -> {
                    Utils.clearScreen();
                    System.out.println();
                    System.out.println("╔══════════════════════╤═════════════════════╗");
                    System.out.printf("║ %-20s │ Score: %-12d ║\n", game.getMain().getDatabase().getCurrentUser().getUsername(), game.getScore().get());
                    System.out.println("╚══════════════════════╧═════════════════════╝");
                    this.printUpgrades();
                    System.out.println("5. Exit");
                    System.out.print(">> ");
                }, List.of(new IntRequirement(
                        input -> input >= 1 && input <= 5,
                        "[!] Invalid choice, please try again."
                ))
        );


        RestaurantUpgrade upgrade;
        switch (choice) {
            case 1:
                upgrade = game.getUpgrades().get(0);
                break;
            case 2:
                upgrade = game.getUpgrades().get(1);
                break;
            case 3:
                upgrade = game.getUpgrades().get(2);
                break;
            case 4:
                upgrade = game.getUpgrades().get(3);
                break;
            default:
                game.start();
                return;
        }

        if (upgrade.getMaxUpgrades() <= upgrade.getUpgrades()) {
            System.out.println("[!] You have reached the maximum amount of upgrades for this upgrade.");
            System.out.println("[!] Press enter to continue.");

            game.getMain().getScanner().nextLine();
            this.open();
            return;
        }

        int cost = upgrade.getFinalCost();
        if (cost > game.getScore().get()) {
            System.out.println("[!] You don't have enough score to buy " + upgrade.getName() + ".");
            System.out.println("[!] You need " + (cost - game.getScore().get()) + " more score.");
            System.out.println("[!] Press enter to continue...");

            game.getMain().getScanner().nextLine();
            this.open();
            return;
        }

        upgrade.applyEffect(game);
        upgrade.setUpgrades(upgrade.getUpgrades() + 1);
        game.getScore().addAndGet(-cost);

        System.out.println("[!] You have successfully bought " + upgrade.getName() + " for " + cost + " score.");
        System.out.println("[!] Press enter to continue...");

        game.getMain().getScanner().nextLine();
        this.open();
    }

    private void printUpgrades() {
        int i = 1;
        for (RestaurantUpgrade upgrade : game.getUpgrades()) {
            System.out.printf("%d. %-18s - %d\n", i, upgrade.getName(), upgrade.getFinalCost());
            i++;
        }
    }
}
