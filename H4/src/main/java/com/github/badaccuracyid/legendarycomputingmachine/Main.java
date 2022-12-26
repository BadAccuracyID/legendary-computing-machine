package com.github.badaccuracyid.legendarycomputingmachine;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.database.FileManager;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menus;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.MainMenu;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Scanner;

public class Main {

    private static Main instance;
    private final Scanner scanner;
    private final Database database;

    public Main() {
        instance = this;
        this.scanner = new Scanner(System.in);
        this.database = new Database();

        new FileManager(database).read();
        Menu.loadMenus(this, new Menus());
        Menu.openMenu(MainMenu.class, null, this);
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Utils.clearScreen();

            Utils.printLCMLogo();
            System.out.println();
            System.out.println("                                ~ RedJackets 23-1 ~");
            System.out.println("            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam a.");
        }));

        new Main();
    }

    public static Main getInstance() {
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Database getDatabase() {
        return database;
    }
}
