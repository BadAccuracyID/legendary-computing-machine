package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.objects.Food;
import com.github.badaccuracyid.legendarycomputingmachine.objects.FoodType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    private final List<Food> foodList;

    public Database() {
        foodList = new ArrayList<>();
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public List<Food> getFoodByType(FoodType foodType) {
        return foodList.stream().filter(it -> it.getFoodType() == foodType).collect(Collectors.toList());
    }
}
