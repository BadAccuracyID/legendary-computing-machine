package com.github.badaccuracyid.legendarycomputingmachine.game.tasks;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;

import java.util.Scanner;

public class InputReaderTask implements Runnable {

    private final Game game;
    private Scanner scanner;

    public InputReaderTask(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String input = scanner.nextLine();
        game.processInput(input);
    }

    public void close() {
        scanner = null;
    }
}
