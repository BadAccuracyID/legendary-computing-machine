package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Drink;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Food;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileManager {

    private final Database database;

    public FileManager(Main main) {
        this.database = main.getDatabase();
    }

    public void read() {
        try {
            this.readUserData();
            this.readDrinksData();
            this.readFoodData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAll() {
        try {
            this.saveUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readUserData() throws IOException {
        File file = new File("users.txt");
        if (!file.exists()) {
            // create
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("#");
                UserData userData = new UserData(split[0], split[1]);
                userData.setHighScore(Integer.parseInt(split[2]));
                database.getUserDataList().add(userData);
            }
        }
    }

    private void readDrinksData() throws IOException {
        File file = new File("drink.txt");
        if (!file.exists()) {
            // create
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("#");

                Drink newDrink = new Drink(split[0], Integer.parseInt(split[2]));
                database.getConsumableList().add(newDrink);
            }
        }
    }

    private void readFoodData() throws IOException {
        File file = new File("food.txt");
        if (!file.exists()) {
            // create
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("#");

                Food newFood = new Food(split[0], Integer.parseInt(split[2]));
                database.getConsumableList().add(newFood);
            }
        }
    }

    protected void saveUserData() throws IOException {
        File file = new File("users.txt");
        if (!file.exists()) {
            // create
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (UserData userData : database.getUserDataList()) {
                writer.write(userData.getUsername() + "#" + userData.getPassword() + "#" + userData.getHighScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}