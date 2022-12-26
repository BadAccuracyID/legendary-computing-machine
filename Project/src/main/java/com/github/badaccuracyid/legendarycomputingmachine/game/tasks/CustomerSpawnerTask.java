package com.github.badaccuracyid.legendarycomputingmachine.game.tasks;

import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.RestaurantAttribute;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Consumable;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class CustomerSpawnerTask implements Runnable {

    private final Game game;

    public CustomerSpawnerTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        List<Customer> customerList = game.getCustomerList();
        RestaurantAttribute restaurantAttribute = game.getRestaurantAttribute();
        if (customerList.size() >= restaurantAttribute.getMaxCustomers()) {
            return;
        }

        int max = 15 + restaurantAttribute.getCustomerAttractionModifier();
        boolean willSpawn = Utils.getRandomInt(0, 100) <= Utils.getRandomInt(10, max);
        if (willSpawn) {
            Database database = game.getMain().getDatabase();
            Consumable randomFood = database.getFoodList().get(Utils.getRandomInt(0, database.getFoodList().size() - 1));
            Consumable randomDrink = database.getDrinkList().get(Utils.getRandomInt(0, database.getDrinkList().size() - 1));

            customerList.add(new Customer(10, randomFood, randomDrink));
        }
    }
}
