package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.Size;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.Animal;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl.Catfish;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl.Cow;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl.Duck;
import com.github.badaccuracyid.legendarycomputingmachine.data.animal.impl.Salmon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {

    private final List<Animal> boughtAnimals;
    private int farmLevel = 1;
    private int maxLandSize = 20;
    private int maxFreshWaterSize = 10;
    private int maxSaltWaterSize = 10;
    private int money = 5000;

    public Database() {
        boughtAnimals = new ArrayList<>();
        this.loadDefaultAnimals();
    }

    public List<Animal> getBoughtAnimals() {
        return boughtAnimals;
    }

    public int getMaxLandSize() {
        return maxLandSize;
    }

    public int getMaxFreshWaterSize() {
        return maxFreshWaterSize;
    }

    public int getMaxSaltWaterSize() {
        return maxSaltWaterSize;
    }

    public int getMoney() {
        return money;
    }

    public Animal generateNewAnimal(String name) {
        switch (name.toLowerCase()) {
            case "cow":
                return new Cow();
            case "duck":
                return new Duck();
            case "catfish":
                return new Catfish();
            case "salmon":
                return new Salmon();
            default:
                return null;
        }
    }

    public int getAnimalSpaceCount(Habitat habitat) {
        int count = 0;
        for (Animal animal : boughtAnimals) {
            if (animal.getHabitat() == habitat) {
                count += animal.getSpace();
            }
        }
        return count;
    }

    public boolean buyAnimal(Animal animal) {
        if (animal.calculatePrice() > money) {
            System.out.println("You don't have enough money to buy this animal!");
            return false;
        }

        switch (animal.getHabitat()) {
            case LAND:
                if (this.getAnimalSpaceCount(animal.getHabitat()) + animal.getSpace() > maxLandSize) {
                    System.out.println("You don't have enough space to buy this animal!");
                    return false;
                }
                break;
            case FRESH_WATER:
                if (this.getAnimalSpaceCount(animal.getHabitat()) + animal.getSpace() > maxFreshWaterSize) {
                    System.out.println("You don't have enough space to buy this animal!");
                    return false;
                }
                break;
            case SALT_WATER:
                if (this.getAnimalSpaceCount(animal.getHabitat()) + animal.getSpace() > maxSaltWaterSize) {
                    System.out.println("You don't have enough space to buy this animal!");
                    return false;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + animal.getHabitat());
        }

        money -= animal.calculatePrice();
        boughtAnimals.add(animal);
        return true;
    }

    public void feedAllAnimals() {
        List<Animal> toFeed = this.boughtAnimals.stream()
                .filter(it -> it.getSize() != Size.LARGE)
                .collect(Collectors.toList());

        int size = toFeed.size();
        if (size == 0) {
            if (this.boughtAnimals.size() > 0) {
                System.out.println("All animals are already large!");
                return;
            }

            System.out.println("You don't have any animals to feed!");
            return;
        }

        int price = size * 50;
        if (price > money) {
            System.out.println("You don't have enough money to feed all your animals!");
            return;
        }

        money -= price;
        toFeed.forEach(Animal::feed);
        System.out.println("You have successfully fed all your animals!");
    }

    public void sellAnimal(Animal animal) {
        if (!boughtAnimals.contains(animal)) {
            System.out.println("You don't have this animal!");
            return;
        }

        money += animal.calculateSellPrice();
        boughtAnimals.remove(animal);

        System.out.println("You sold " + animal.getName() + " for " + animal.calculateSellPrice() + ".");
    }

    public void sellAllAnimals() {
        if (boughtAnimals.size() == 0) {
            System.out.println("You don't have any animals to sell!");
            return;
        }

        int price = 0;
        for (Animal animal : boughtAnimals) {
            price += animal.calculateSellPrice();
        }

        money += price;
        boughtAnimals.clear();

        System.out.println("You sold all your animals for " + price + ".");
    }

    public void upgradeFarm() {
        int cost = 1000 + (800 * farmLevel);
        if (cost > money) {
            System.out.println("You don't have enough money to upgrade your farm!");
            return;
        }

        money -= cost;
        farmLevel++;
        maxLandSize += 20 + (farmLevel * 4);
        maxFreshWaterSize += 10 + (farmLevel * 2);
        maxSaltWaterSize += 10 + (farmLevel * 2);

        System.out.println("Your farm has been upgraded to level " + farmLevel + "!");
    }

    private void loadDefaultAnimals() {
        Cow cow = new Cow();
        cow.setName("Alex");
        cow.setSize(Size.SMALL);
        cow.setAnimalType("Angus Cow");
        boughtAnimals.add(cow);

        cow = new Cow();
        cow.setName("Budi");
        cow.setSize(Size.SMALL);
        cow.setAnimalType("Hereford Cow");
        boughtAnimals.add(cow);

        Catfish catfish = new Catfish();
        catfish.setName("Caty");
        catfish.setSize(Size.MEDIUM);
        catfish.setAnimalType("Channel Catfish");
        boughtAnimals.add(catfish);

        Duck duck = new Duck();
        duck.setName("Donaly");
        duck.setSize(Size.LARGE);
        duck.setAnimalType("Bali Duck");
        boughtAnimals.add(duck);

        duck = new Duck();
        duck.setName("Ducky");
        duck.setSize(Size.MEDIUM);
        duck.setAnimalType("Campbell Duck");
        boughtAnimals.add(duck);

        Salmon salmon = new Salmon();
        salmon.setName("Gol Sal");
        salmon.setSize(Size.MEDIUM);
        salmon.setAnimalType("Sockeye Salmon");
        boughtAnimals.add(salmon);

        catfish = new Catfish();
        catfish.setName("Leley");
        catfish.setSize(Size.LARGE);
        catfish.setAnimalType("Blue Catfish");
        boughtAnimals.add(catfish);

        salmon = new Salmon();
        salmon.setName("Salmony");
        salmon.setSize(Size.SMALL);
        salmon.setAnimalType("Chinook Salmon");
        boughtAnimals.add(salmon);
    }
}
