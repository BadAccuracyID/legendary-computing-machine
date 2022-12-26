package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

public class ScoreboardMenu extends Menu {

    public ScoreboardMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        Utils.clearScreen();

        main.getDatabase().getUserDataListSorted().forEach(user -> {
            System.out.println("╔══════════════════════╤═════════════════════╗");
            System.out.printf("║ %-20s │ Score: %-12d ║\n", user.getUsername(), user.getHighScore());
            System.out.println("╚══════════════════════╧═════════════════════╝");
        });

        this.enterToGoBack();
    }
}
