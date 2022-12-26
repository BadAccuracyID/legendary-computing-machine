package com.github.badaccuracyid.legendarycomputingmachine.game;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.game.tasks.CustomerSpawnerTask;
import com.github.badaccuracyid.legendarycomputingmachine.game.tasks.DrawerTask;
import com.github.badaccuracyid.legendarycomputingmachine.game.tasks.InputReaderTask;
import com.github.badaccuracyid.legendarycomputingmachine.game.tasks.TickTask;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.RestaurantUpgrade;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.UpgradeInterface;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl.BuyDecorations;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl.ExpandRestaurant;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl.HireWorker;
import com.github.badaccuracyid.legendarycomputingmachine.game.upgrades.impl.PurchaseAds;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.MainMenu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private final Main main;
    private final ExecutorManager executorManager;
    private final AtomicInteger time;
    private final AtomicInteger score;
    private final AtomicInteger life;
    private final List<Customer> customerList;
    private final List<Worker> workerList;
    private final RestaurantAttribute restaurantAttribute;
    private final List<RestaurantUpgrade> upgrades;

    private InputReaderTask inputReaderTask;

    boolean nextRoundPaused = false;
    boolean lostPaused = false;

    public Game(Main main) {
        this.main = main;
        this.executorManager = new ExecutorManager();
        this.time = new AtomicInteger(0);
        this.score = new AtomicInteger(0);
        this.life = new AtomicInteger(5);
        this.customerList = Collections.synchronizedList(new ArrayList<>());
        this.workerList = Collections.synchronizedList(new ArrayList<>());
        this.restaurantAttribute = new RestaurantAttribute();

        this.upgrades = new ArrayList<>();
        this.upgrades.add(new BuyDecorations());
        this.upgrades.add(new ExpandRestaurant());
        this.upgrades.add(new HireWorker());
        this.upgrades.add(new PurchaseAds());
    }

    public void start() {
        Utils.clearScreen();
        nextRoundPaused = false;
        lostPaused = false;

        TickTask tickTask = new TickTask(this);
        this.executorManager.createOrGetSingleThreadScheduledExecutor("Timer")
                .scheduleAtFixedRate(tickTask, 0, 1, TimeUnit.SECONDS);

        DrawerTask drawerTask = new DrawerTask(this);
        this.executorManager.createOrGetSingleThreadScheduledExecutor("Drawer")
                .scheduleAtFixedRate(drawerTask, 0, 250, TimeUnit.MILLISECONDS);

        inputReaderTask = new InputReaderTask(this);
        this.executorManager.createOrGetSingleThreadScheduledExecutor("InputReader")
                .scheduleAtFixedRate(inputReaderTask, 0, 125, TimeUnit.MILLISECONDS);

        CustomerSpawnerTask customerSpawnerTask = new CustomerSpawnerTask(this);
        this.executorManager.createOrGetSingleThreadScheduledExecutor("CustomerSpawner")
                .scheduleAtFixedRate(customerSpawnerTask, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        inputReaderTask.close();
        executorManager.hardShutdown();

        UserData currentUser = main.getDatabase().getCurrentUser();
        if (score.get() > currentUser.getHighScore()) {
            currentUser.setHighScore(score.get());
            main.getFileManager().saveAll();
        }
    }

    private void end() {
        customerList.clear();
        workerList.clear();
        upgrades.clear();
        life.set(0);
        score.set(0);
        time.set(0);
        inputReaderTask = null;
        nextRoundPaused = false;
        lostPaused = false;
    }

    public void processInput(String input) {
        if (input.equalsIgnoreCase("exit")) {
            this.stop();
            this.end();
            Menu.openMenu(MainMenu.class, null, main);
            return;
        }

        if (nextRoundPaused) {
            if (input.equalsIgnoreCase("continue")) {
                this.customerList.clear();
                Utils.clearScreen();
                new UpgradeInterface(this).open();
                return;
            }

            this.onNextRound();
            input = main.getScanner().nextLine();
            this.processInput(input);
            return;
        }

        if (lostPaused) {
            Utils.clearScreen();
            this.end();
            Menu.openMenu(MainMenu.class, null, main);
            return;
        }

        // search for the customer
        for (Customer customer : customerList) {
            if (customer.onOrderPrepare(input)) {
                break;
            }
        }
    }

    public void onNextRound() {
        this.stop();
        nextRoundPaused = true;

        System.out.println();
        System.out.println("██      ███████ ██    ██ ███████ ██           ██████ ██      ███████  █████  ██████  ███████ ██████  \n" +
                "██      ██      ██    ██ ██      ██          ██      ██      ██      ██   ██ ██   ██ ██      ██   ██ \n" +
                "██      █████   ██    ██ █████   ██          ██      ██      █████   ███████ ██████  █████   ██   ██ \n" +
                "██      ██       ██  ██  ██      ██          ██      ██      ██      ██   ██ ██   ██ ██      ██   ██ \n" +
                "███████ ███████   ████   ███████ ███████      ██████ ███████ ███████ ██   ██ ██   ██ ███████ ██████  \n");
        System.out.println("Type 'exit' to stop playing or 'continue' to continue...");
    }

    public void onLost() {
        this.stop();
        lostPaused = true;

        System.out.println();
        System.out.println("██    ██  ██████  ██    ██     ██       ██████  ███████ ████████ \n" +
                " ██  ██  ██    ██ ██    ██     ██      ██    ██ ██         ██    \n" +
                "  ████   ██    ██ ██    ██     ██      ██    ██ ███████    ██    \n" +
                "   ██    ██    ██ ██    ██     ██      ██    ██      ██    ██    \n" +
                "   ██     ██████   ██████      ███████  ██████  ███████    ██    \n");
        System.out.println("Press enter to continue...");
    }

    public Main getMain() {
        return main;
    }

    public AtomicInteger getTime() {
        return time;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public AtomicInteger getLife() {
        return life;
    }

    public AtomicInteger getScore() {
        return score;
    }

    public RestaurantAttribute getRestaurantAttribute() {
        return restaurantAttribute;
    }

    public List<RestaurantUpgrade> getUpgrades() {
        return upgrades;
    }
}
