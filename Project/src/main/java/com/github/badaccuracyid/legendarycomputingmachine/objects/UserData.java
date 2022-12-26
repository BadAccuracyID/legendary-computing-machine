package com.github.badaccuracyid.legendarycomputingmachine.objects;

import java.util.Objects;

public class UserData {

    private final String username, password;
    private int highScore = 0;

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return username.equals(userData.username) && password.equals(userData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
