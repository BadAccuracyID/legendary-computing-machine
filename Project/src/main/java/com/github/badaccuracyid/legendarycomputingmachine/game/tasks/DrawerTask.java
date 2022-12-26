package com.github.badaccuracyid.legendarycomputingmachine.game.tasks;

import com.github.badaccuracyid.legendarycomputingmachine.game.Game;
import com.github.badaccuracyid.legendarycomputingmachine.game.Worker;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.List;

public class DrawerTask implements Runnable {

    private final Game game;

    public DrawerTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        this.drawInterface();
    }

    private void drawInterface() {
        Utils.clearScreen();
        System.out.println("╔═══════════╤═════════╤═════════════════════╗");
        System.out.printf("║ Time: %-3d │ Life: %-1d │ Score: %-12d ║\n", game.getTime().get(), game.getLife().get(), game.getScore().get());
        System.out.println("╚═══════════╧═════════╧═════════════════════╝");

        this.drawWorkersTable();
        System.out.println();
        System.out.println("╔════╤════════════╤══════════════════════════╤══════════════════════════╤═════════╗");
        System.out.printf("║ %-2s │ %-10s │ %-24s │ %-24s │ %-7s ║\n", "No", "Patience", "Food", "Drink", "Price");
        System.out.println("╠════╪════════════╪══════════════════════════╪══════════════════════════╪═════════╣");
        this.drawCustomersTable();
        System.out.println("╚════╧════════════╧══════════════════════════╧══════════════════════════╧═════════╝");
        System.out.println("Type the order to serve (case sensitive)");
        System.out.println("Type exit to stop playing");
    }

    private void drawWorkersTable() {
        List<Worker> workerList = game.getWorkerList();
        if (workerList.size() == 0) {
            return;
        }

        System.out.println("╔════════╤════════════╗");
        System.out.printf("║ %-6s │ %-10s ║\n", "Worker", "Cooking");
        System.out.println("╠════════╪════════════╣");
        for (int i = 0; i < workerList.size(); i++) {
            Worker worker = workerList.get(i);
            System.out.printf("║ %-6s │ %-10s ║\n", i + 1, worker.getProgress());
        }
        System.out.println("╚════════╧════════════╝");
    }

    private void drawCustomersTable() {
        List<Customer> customerList = game.getCustomerList();
        for (int i = 0; i < game.getRestaurantAttribute().getMaxCustomers(); i++) {
            if (customerList.size() > i) {
                Customer customer = customerList.get(i);
                System.out.printf("║ %-2d │ %-10s │ %-24s │ %-24s │ %-7d ║\n", i + 1, customer.getPatienceBar(), customer.getFoodName(), customer.getDrinkName(), customer.getTotalPrice());
            } else {
                System.out.printf("║ %-2d │ %-10s │ %-24s │ %-24s │ %-7s ║\n", i + 1, "", "", "", "");
            }
        }
    }

}
