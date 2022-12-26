package com.github.badaccuracyid.legendarycomputingmachine.menu;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    private static final List<Menu> menus = new ArrayList<>();
    protected static final Main main = Main.getInstance();
    protected static Menu lastMenu = null;

    protected Menu() {
        menus.add(this);
    }

    protected abstract void showMenu();

    public void open(Class<? extends Menu> clazz) {
        lastMenu = this;

        menus.stream()
                .filter(menu -> menu.getClass().equals(clazz))
                .findFirst()
                .ifPresent(Menu::showMenu);
    }

    public static void openMenu(Class<? extends Menu> clazz, Class<? extends Menu> laztMenu) {
        // yes i'm bad at variable naming
        Menu lazztMenu = menus.stream()
                .filter(menu1 -> menu1.getClass().equals(laztMenu))
                .findFirst()
                .orElse(null);

        assert lazztMenu != null;
        lastMenu = lazztMenu;

        menus.stream()
                .filter(menu -> menu.getClass().equals(clazz))
                .findFirst()
                .ifPresent(Menu::showMenu);
    }

    protected void enterOrGoBack() {
        System.out.println("Press enter to continue...");
        main.getScanner().nextLine();

        if (lastMenu != null) {
            lastMenu.showMenu();
        } else {
            Utils.clearScreen();
            System.out.println("No last menu!");
        }
    }

}
