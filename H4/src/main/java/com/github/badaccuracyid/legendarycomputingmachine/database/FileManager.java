package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.objects.Food;
import com.github.badaccuracyid.legendarycomputingmachine.objects.FoodType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private final Database database;

    public FileManager(Database database) {
        this.database = database;
    }

    public void read() {
        // foodtype#food name#price#preptime#origin#isFrozen

        File file = new File("source.txt");
        if (!file.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("#");

                Food newFood;
                FoodType foodType = FoodType.valueOf(split[0].toUpperCase());
                String foodName = split[1];
                int price = Integer.parseInt(split[2]);
                int preparationTime = Integer.parseInt(split[3]);

                if (split.length == 6) {
                    String foodOrigin = split[4];
                    String lastParam = split[5];
                    newFood = new Food(foodType, foodName, foodOrigin, price, preparationTime, lastParam);
                } else {
                    String lastParam = split[4];
                    newFood = new Food(foodType, foodName, price, preparationTime, lastParam);
                }
                database.addFood(newFood);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
