package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

public class LoginMenu extends Menu {

    public LoginMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Utils.printLocalLogo();

        String username;
        System.out.print("Enter your username: ");
        username = main.getScanner().nextLine();

        String password;
        System.out.print("Enter your password: ");
        password = main.getScanner().nextLine();

        boolean loggedIn = main.getDatabase().tryLogin(username, password);
        if (loggedIn) {
            System.out.println("Logged in successfully!");
            this.enterToContinue();

            this.open(MainMenu.class);
        } else {
            System.out.println("Invalid username or password!");
            this.enterToGoBack();
        }
    }
}
