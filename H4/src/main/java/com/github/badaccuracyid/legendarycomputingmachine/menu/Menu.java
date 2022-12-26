package com.github.badaccuracyid.legendarycomputingmachine.menu;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Menu {

    private static final List<Menu> menus = new ArrayList<>();
    private static Menu lastMenu = null;
    protected final Main main;

    public Menu(Main main) {
        this.main = main;
        menus.add(this);
    }

    protected abstract void showMenu();

    public void open(Class<? extends Menu> clazz) {
        lastMenu = this;

        Optional<Menu> optionalMenu = menus.stream()
                .filter(menu -> menu.getClass().equals(clazz))
                .findFirst();

        if (optionalMenu.isPresent()) {
            optionalMenu.get().showMenu();
            return;
        }

        try {
            Field field = clazz.getDeclaredField("main");
            field.setAccessible(true);
            field.set(null, main);
            field.setAccessible(false);

            Menu menu = clazz.getDeclaredConstructor().newInstance();
            menu.showMenu();
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void openMenu(Class<? extends Menu> clazz, Class<? extends Menu> laztMenu, Main main) {
        // yes i'm bad at variable naming
        Menu lazztMenu = menus.stream()
                .filter(menu1 -> menu1.getClass().equals(laztMenu))
                .findFirst()
                .orElse(null);

        assert lazztMenu != null;
        lastMenu = lazztMenu;

        Optional<Menu> optionalMenu = menus.stream()
                .filter(menu -> menu.getClass().equals(clazz))
                .findFirst();

        if (optionalMenu.isPresent()) {
            optionalMenu.get().showMenu();
            return;
        }

        try {
            Field field = clazz.getDeclaredField("main");
            field.setAccessible(true);
            field.set(null, main);
            field.setAccessible(false);

            Menu menu = clazz.getDeclaredConstructor().newInstance();
            menu.showMenu();
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void loadMenus(Main main, Object menus) {
        // load all menus in  "com.github.badaccuracyid.legendarycomputingmachine.menu.impl
        for (Field field : menus.getClass().getDeclaredFields()) {
            try {
                boolean accessible = field.isAccessible();

                field.setAccessible(true);
                field.set(menus, field.getType().getDeclaredConstructor(Main.class).newInstance(main));
                System.out.println("[+] Loaded menu: " + field.getType().getSimpleName());

                field.setAccessible(accessible);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
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

    protected void enterToContinue() {
        System.out.println("Press enter to continue...");
        main.getScanner().nextLine();
    }

}
