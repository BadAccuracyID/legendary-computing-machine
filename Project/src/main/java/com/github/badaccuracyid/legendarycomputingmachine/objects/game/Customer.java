package com.github.badaccuracyid.legendarycomputingmachine.objects.game;

import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private final AtomicInteger patience;
    private final Consumable food, drink;
    private boolean foodPrepared, drinkPrepared;

    public Customer(int patience, Consumable food, Consumable drink) {
        this.patience = new AtomicInteger(patience);
        this.food = food;
        this.drink = drink;
    }

    public int getPatience() {
        return patience.get();
    }

    public String getPatienceBar() {
        StringBuilder patienceBar = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            if (i < patience.get()) {
                patienceBar.append("█");
            } else {
                patienceBar.append("░");
            }
        }

        return patienceBar.toString();
    }

    public void tick(int chance) {
        if (patience.get() <= 0) {
            return;
        }

        if (Utils.getRandomInt(0, 100) <= chance) {
            patience.decrementAndGet();
        }
    }

    public String getFoodName() {
        StringBuilder foodName = new StringBuilder(this.food.getName());
        if (foodPrepared) {
            foodName.append(" [v]");
        } else {
            foodName.append(" [ ]");
        }

        return foodName.toString();
    }

    public String getDrinkName() {
        StringBuilder drinkName = new StringBuilder(this.drink.getName());
        if (drinkPrepared) {
            drinkName.append(" [v]");
        } else {
            drinkName.append(" [ ]");
        }

        return drinkName.toString();
    }

    public int getTotalPrice() {
        int total = 0;
        total += food.getPrice() * patience.get();
        total += drink.getPrice() * patience.get();
        return total;
    }

    public boolean onOrderPrepare(String order) {
        if (order.equals(food.getName())) {
            foodPrepared = true;
            return true;
        } else if (order.equals(drink.getName())) {
            drinkPrepared = true;
            return true;
        }

        return false;
    }

    public boolean isFoodPrepared() {
        return foodPrepared;
    }

    public boolean isDrinkPrepared() {
        return drinkPrepared;
    }

    public Consumable getFood() {
        return food;
    }

    public Consumable getDrink() {
        return drink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return food.equals(customer.food) && drink.equals(customer.drink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, drink);
    }
}
