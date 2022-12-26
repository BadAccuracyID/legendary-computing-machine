package com.github.badaccuracyid.legendarycomputingmachine;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.Size;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;
import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final Database database = new Database();

    public Main() {
        this.mainMenu();
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Utils.clearScreen();

            Utils.printLCMLogo();
            System.out.println();
            System.out.println("                                - RedJackets 23-1 ~!");
            System.out.println("            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam a.");
        }));

        new Main();
    }

    private void mainMenu() {
        int choice = 0;
        do {
            Utils.clearScreen();
            this.printAnimalTable();
            System.out.println();
            System.out.println();
            System.out.println();

            System.out.println("Your money: " + database.getMoney());
            System.out.println("Land size capacity: " + database.getAnimalSpaceCount(Habitat.LAND) + "/" + database.getMaxLandSize());
            System.out.println("Fresh water size capacity: " + database.getAnimalSpaceCount(Habitat.FRESH_WATER) + "/" + database.getMaxFreshWaterSize());
            System.out.println("Salt water size capacity: " + database.getAnimalSpaceCount(Habitat.SALT_WATER) + "/" + database.getMaxSaltWaterSize());
            System.out.println();
            System.out.println();

            System.out.println("FarmLy");
            System.out.println("1. Buy Animal");
            System.out.println("2. Feed All Animals");
            System.out.println("3. Sell Animal");
            System.out.println("4. Sell All Animal");
            System.out.println("5. Upgrade Farm");
            System.out.println("6. Exit");
            System.out.print(">> ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (choice < 1 || choice > 6);

        switch (choice) {
            case 1:
                this.buyAnimal();
                break;
            case 2:
                this.feedAllAnimals();
                break;
            case 3:
                this.sellAnimal();
                break;
            case 4:
                this.sellAllAnimals();
                break;
            case 5:
                this.upgradeFarm();
                break;
            case 6:
                System.exit(0);
                break;
        }
    }

    private void printAnimalTable() {
        List<Animal> boughtAnimals = database.getBoughtAnimals();
        if (boughtAnimals.isEmpty()) {
            System.out.println("You don't have any animals!");
            return;
        }

        System.out.println("==========================================================================================================");
        System.out.printf("| %-3s | %-20s | %-10s | %-15s | %-10s | %-30s|\n", "No.", "Name", "Size", "Habitat", "Food Count", "Type");
        System.out.println("==========================================================================================================");

        for (int i = 0; i < boughtAnimals.size(); i++) {
            Animal animal = boughtAnimals.get(i);
            System.out.printf("| %-3s | %-20s | %-10s | %-15s | %-10s | %-30s|\n", i + 1 + ".", animal.getName(), animal.getSize().getName(), animal.getHabitat().getName(), animal.getFoodCount(), animal.getAnimalType());
        }

        System.out.println("==========================================================================================================");

    }

    private void buyAnimal() {
        Utils.clearScreen();
        Animal newAnimal;

        String inputHabitat;
        do {
            System.out.print("Input animal habitat [Land | Water]: ");
            inputHabitat = scanner.nextLine();
        } while (!inputHabitat.equals("Land") && !inputHabitat.equals("Water"));
        Habitat habitat = Habitat.valueOf(inputHabitat.toUpperCase());

        String animal;
        do {
            System.out.print("Input animal [");
            Iterator<String> animals = habitat.getAnimals();
            while (animals.hasNext()) {
                System.out.print(animals.next());
                if (animals.hasNext()) {
                    System.out.print(" | ");
                }
            }
            System.out.print("]: ");

            animal = scanner.nextLine();
        } while (!habitat.isValidAnimal(animal));
        newAnimal = database.generateNewAnimal(animal);

        String name;
        do {
            System.out.print("Input animal name [3 .. 15 characters]: ");
            name = scanner.nextLine();
        } while (name.length() < 3 || name.length() > 15);
        newAnimal.setName(name);

        String inputSize;
        do {
            System.out.print("Input animal size [Small | Medium | Large]: ");
            inputSize = scanner.nextLine();
        } while (!inputSize.equals("Small") && !inputSize.equals("Medium") && !inputSize.equals("Large"));
        Size size = Size.valueOf(inputSize.toUpperCase());
        newAnimal.setSize(size);

        String animalType;
        do {
            System.out.print("Input animal type [");
            Iterator<String> types = newAnimal.getTypes().iterator();
            while (types.hasNext()) {
                System.out.print(types.next());
                if (types.hasNext()) {
                    System.out.print(" | ");
                }
            }
            System.out.print("]: ");

            animalType = scanner.nextLine();
        } while (!newAnimal.getTypes().contains(animalType));
        newAnimal.setAnimalType(animalType);

        boolean bought = database.buyAnimal(newAnimal);
        if (bought) {
            System.out.println("Successfully bought " + animal + "!");
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
        this.mainMenu();
    }

    private void feedAllAnimals() {
        System.out.println();

        database.feedAllAnimals();
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        this.mainMenu();
    }

    private void sellAnimal() {
        List<Animal> boughtAnimals = database.getBoughtAnimals();

        int choice = 0;
        do {
            Utils.clearScreen();
            this.printAnimalTable();
            System.out.println();
            System.out.println();
            System.out.println();

            System.out.print("Input animal number to sell [1 - " + boughtAnimals.size() + "]: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (choice < 1 || choice > boughtAnimals.size());

        Animal animal = boughtAnimals.get(choice - 1);
        database.sellAnimal(animal);

        System.out.println("Press enter to continue...");
        scanner.nextLine();
        this.mainMenu();
    }

    private void sellAllAnimals() {
        System.out.println();

        database.sellAllAnimals();
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        this.mainMenu();
    }

    private void upgradeFarm() {
        System.out.println();

        database.upgradeFarm();
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        this.mainMenu();
    }
}
