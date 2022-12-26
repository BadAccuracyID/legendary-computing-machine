package com.github.badaccuracyid.legendarycomputingmachine.menu.impl;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.StrRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.utils.InputUtils;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Arrays;

public class RegisterMenu extends Menu {

    public RegisterMenu(Main main) {
        super(main);
    }

    @Override
    protected void showMenu() {
        Utils.clearScreen();
        Utils.printLocalLogo();

        String username = InputUtils.determineStrChoice.apply(
                () -> System.out.print("Enter a unique username [5 .. 20]: "),
                Arrays.asList(new StrRequirement(
                        input -> input.length() >= 5 && input.length() <= 20,
                        "[!] Username must be between 5 and 20 characters long."
                ), new StrRequirement(
                        input -> !main.getDatabase().userExists(input),
                        "[!] Username already exists."
                )));

        String password = InputUtils.determineStrChoice.apply(
                () -> System.out.print("Enter a password [8 .. 20]: "),
                Arrays.asList(new StrRequirement(
                        input -> input.length() >= 8 && input.length() <= 20,
                        "[!] Password must be between 5 and 20 characters long."
                )));

        UserData newUser = new UserData(username, password);
        main.getDatabase().registerUser(newUser);
        assert main.getDatabase().tryLogin(username, password);

        System.out.println("Registered successfully!");
        this.enterToContinue();

        this.open(MainMenu.class);
    }
}
