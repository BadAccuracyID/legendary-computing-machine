package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class HomeMenu extends Menu {

    public HomeMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        int choice = InputUtils.determineIntChoice.apply(
                () -> {
                    Utils.clearScreen();
                    Utils.printLocalLogo();

                    System.out.println();
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.println("3. Exit");
                    System.out.print(">> ");
                },
                List.of(
                        new IntRequirement(
                                input -> input >= 1 && input <= 3,
                                "[!] Invalid choice, please try again."
                        )
                ));

        switch (choice) {
            case 1:
                this.open(LoginMenu.class);
                break;
            case 2:
                this.open(RegisterMenu.class);
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
}
