package com.github.badaccuracyid.legendarycomputingmachine.database;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Consumable;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Sorter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final Main main;
    private final List<UserData> userDataList;
    private final List<Consumable> consumableList;
    private UserData currentUser;

    public Database(Main main) {
        this.main = main;
        this.userDataList = new ArrayList<>();
        this.consumableList = new ArrayList<>();
    }

    public UserData getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserData currentUser) {
        this.currentUser = currentUser;
    }

    protected List<UserData> getUserDataList() {
        return userDataList;
    }

    protected List<Consumable> getConsumableList() {
        return consumableList;
    }

    public List<UserData> getUserDataListSorted() {
        List<UserData> sortedList = new ArrayList<>(userDataList);

        Sorter.mergeSort.accept(sortedList);
        return sortedList;
    }

    public boolean tryLogin(String username, String password) {
        for (UserData userData : userDataList) {
            if (userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
                setCurrentUser(userData);
                return true;
            }
        }
        return false;
    }

    public boolean userExists(String username) {
        for (UserData userData : userDataList) {
            if (userData.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(UserData newUser) {
        userDataList.add(newUser);

        try {
            main.getFileManager().saveUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Consumable> getFoodList() {
        List<Consumable> foodList = new ArrayList<>();
        for (Consumable consumable : consumableList) {
            if (consumable.isFood()) {
                foodList.add(consumable);
            }
        }
        return foodList;
    }

    public List<Consumable> getDrinkList() {
        List<Consumable> drinkList = new ArrayList<>();
        for (Consumable consumable : consumableList) {
            if (!consumable.isFood()) {
                drinkList.add(consumable);
            }
        }
        return drinkList;
    }
}
