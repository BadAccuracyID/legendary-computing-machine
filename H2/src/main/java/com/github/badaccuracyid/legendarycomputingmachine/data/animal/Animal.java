package com.github.badaccuracyid.legendarycomputingmachine.data.animal;

import com.github.badaccuracyid.legendarycomputingmachine.data.Habitat;
import com.github.badaccuracyid.legendarycomputingmachine.data.Size;

import java.util.List;

public class Animal {

    private final Habitat habitat;
    private final int price;
    private final int sellPrice;
    private final int space;

    private String name;
    private String animalType;
    private Size size;
    private int foodCount = 0;

    public Animal(Habitat habitat, int price, int sellPrice, int space) {
        this.habitat = habitat;
        this.price = price;
        this.sellPrice = sellPrice;
        this.space = space;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public int getSpace() {
        return space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getFoodCount() {
        return foodCount;
    }

    // override this
    public List<String> getTypes() {
        throw new IllegalStateException("This method should be overridden");
    }

    public int calculatePrice() {
        return this.price + this.getSize().getPrice();
    }

    public int calculateSellPrice() {
        return this.sellPrice + this.getSize().getSellPrice();
    }

    public void feed() {
        if (this.size == Size.LARGE) {
            System.out.println(this.name + " is already large!");
            return;
        }

        this.foodCount++;
        if (this.foodCount >= 3 && this.size == Size.SMALL) {
            this.size = Size.MEDIUM;
            this.foodCount = 0;
        } else if (this.foodCount >= 4 && this.size == Size.MEDIUM) {
            this.size = Size.LARGE;
            this.foodCount = 0;
        }

        System.out.println("Successfully fed " + this.name + "!");
    }
}
