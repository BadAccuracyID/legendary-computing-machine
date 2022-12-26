package com.github.badaccuracyid.legendarycomputingmachine.objects;

import java.util.concurrent.atomic.AtomicInteger;

public class Food {

    private final FoodType foodType;
    private final String foodName, foodOrigin, lastParam;
    private final int price;
    private final AtomicInteger preparationTime = new AtomicInteger(0);

    public Food(FoodType foodType, String foodName, String foodOrigin, int price, int preparationTime, String lastParam) {
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodOrigin = foodOrigin;
        this.price = price;
        this.preparationTime.set(preparationTime);
        this.lastParam = lastParam;
    }

    public Food(FoodType foodType, String foodName, int price, int preparationTime, String lastParam) {
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodOrigin = null;
        this.price = price;
        this.preparationTime.set(preparationTime);
        this.lastParam = lastParam;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodOrigin() {
        return foodOrigin;
    }

    public int getPrice() {
        return price;
    }

    public AtomicInteger getPreparationTime() {
        return preparationTime;
    }

    public boolean isFrozen() {
        return String.valueOf(lastParam).equals("1");
    }

    public boolean isFoodReady() {
        return preparationTime.get() <= 0;
    }

    // clone
    public void tick() {
        int get = preparationTime.decrementAndGet();
        if (get < 0) {
            preparationTime.set(0);
        }
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Food clone() {
        return new Food(foodType, foodName, foodOrigin, price, preparationTime.get(), lastParam);
    }
}
