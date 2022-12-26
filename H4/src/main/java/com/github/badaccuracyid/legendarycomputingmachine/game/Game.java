package com.github.badaccuracyid.legendarycomputingmachine.game;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.menu.Menu;
import com.github.badaccuracyid.legendarycomputingmachine.menu.impl.MainMenu;
import com.github.badaccuracyid.legendarycomputingmachine.objects.Food;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Courier;
import com.github.badaccuracyid.legendarycomputingmachine.objects.game.Customer;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private final Main main;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private final AtomicInteger gameTime = new AtomicInteger(0);
    private final AtomicBoolean doDraw = new AtomicBoolean(true);

    private Customer customer1 = null;
    private Customer customer2 = null;
    private Customer customer3 = null;

    private Food kitchen1 = null;
    private Food kitchen2 = null;
    private Food kitchen3 = null;

    private Courier courier1 = null;
    private Courier courier2 = null;
    private Courier courier3 = null;

    private int moneyEarned = 0;

    public Game(Main main) {
        this.main = main;
        customer1 = new Customer(main);
    }

    public void start() {
        this.startTick();
    }

    public void stop() {
        executor.shutdownNow();
    }

    private void drawInterface() {
        if (!doDraw.get()) {
            return;
        }

        Utils.clearScreen();
        System.out.println("Game Timer: " + gameTime.get());
        System.out.println("Total money earned: IDR " + moneyEarned);
        System.out.println();

        System.out.println("Customers");
        drawCustomers();
        System.out.println();
        System.out.println("Kitchen");
        drawKitchen();
        System.out.println();
        System.out.println("Courier");
        drawCourier();

        System.out.println();
        System.out.println();
        System.out.println("[P] Prepare Food        [A] Assign Courier        [Q] Quit");
        System.out.print(">> ");
    }

    private void drawCustomers() {
        if (customer1 != null) {
            System.out.printf("%-35s (%-3s) | ", "Customer 1", customer1.getPatience() + "s");
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (customer2 != null) {
            System.out.printf("%-35s (%-3s) | ", "Customer 2", customer1.getPatience() + "s");
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (customer3 != null) {
            System.out.printf("%-35s (%-3s) |\n", "Customer 3", customer1.getPatience() + "s");
        } else {
            System.out.printf("%-35s (%-3s) |\n", "-", "-s");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

        if (customer1 != null) {
            System.out.printf("%-41s | ", customer1.getOrder().getFoodType() + " - " + customer1.getOrder().getFoodName());
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (customer2 != null) {
            System.out.printf("%-41s | ", customer2.getOrder().getFoodType() + " - " + customer2.getOrder().getFoodName());
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (customer3 != null) {
            System.out.printf("%-41s |\n", customer3.getOrder().getFoodType() + " - " + customer3.getOrder().getFoodName());
        } else {
            System.out.printf("%-41s |\n", "-");
        }
    }

    private void drawKitchen() {
        if (kitchen1 != null) {
            System.out.printf("%-35s (%-3s) | ", "Order 1", kitchen1.getPreparationTime() + "s");
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (kitchen2 != null) {
            System.out.printf("%-35s (%-3s) | ", "Order 2", kitchen2.getPreparationTime() + "s");
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (kitchen3 != null) {
            System.out.printf("%-35s (%-3s) |\n", "Order 3", kitchen2.getPreparationTime() + "s");
        } else {
            System.out.printf("%-35s (%-3s) |\n", "-", "-s");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

        if (kitchen1 != null) {
            System.out.printf("%-41s | ", kitchen1.getFoodName());
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (kitchen2 != null) {
            System.out.printf("%-41s | ", kitchen2.getFoodName());
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (kitchen3 != null) {
            System.out.printf("%-41s |\n", kitchen3.getFoodName());
        } else {
            System.out.printf("%-41s |\n", "-");
        }
    }

    private void drawCourier() {
        if (courier1 != null) {
            if (courier1.isCollecting()) {
                System.out.printf("%-35s (%-3s) | ", "Courier 1", courier1.getWait() + "s");
            } else {
                System.out.printf("%-41s | ", "Courier 1");
            }
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (courier2 != null) {
            if (courier2.isCollecting()) {
                System.out.printf("%-35s (%-3s) | ", "Courier 2", courier2.getWait() + "s");
            } else {
                System.out.printf("%-41s | ", "Courier 2");
            }
        } else {
            System.out.printf("%-35s (%-3s) | ", "-", "-s");
        }

        if (courier3 != null) {
            if (courier3.isCollecting()) {
                System.out.printf("%-35s (%-3s) |\n", "Courier 3", courier3.getWait() + "s");
            } else {
                System.out.printf("%-41s |\n", "Courier 3");
            }
        } else {
            System.out.printf("%-35s (%-3s) |\n", "-", "-s");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

        if (courier1 != null) {
            if (courier1.isCollecting()) {
                System.out.printf("%-41s | ", "Collecting Food ...");
            } else {
                System.out.printf("%-41s | ", "Delivering Food ... (" + courier1.getDistance() + "km)");
            }
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (courier2 != null) {
            if (courier2.isCollecting()) {
                System.out.printf("%-41s | ", "Collecting Food ...");
            } else {
                System.out.printf("%-41s | ", "Delivering Food ... (" + courier2.getDistance() + "km)");
            }
        } else {
            System.out.printf("%-41s | ", "-");
        }

        if (courier3 != null) {
            if (courier3.isCollecting()) {
                System.out.printf("%-41s |\n", "Collecting Food ...");
            } else {
                System.out.printf("%-41s |\n", "Delivering Food ... (" + courier3.getDistance() + "km)");
            }
        } else {
            System.out.printf("%-41s |\n", "-");
        }
    }

    private void prepareOrder() {
        if (customer1 != null) {
            if (kitchen1 == null) {
                kitchen1 = customer1.getOrder();
                customer1 = null;
            } else if (kitchen2 == null) {
                kitchen2 = customer1.getOrder();
                customer1 = null;
            } else if (kitchen3 == null) {
                kitchen3 = customer1.getOrder();
                customer1 = null;
            } else {
                System.out.println("All kitchen slots are full!");
            }
        }

        if (customer2 != null) {
            if (kitchen1 == null) {
                kitchen1 = customer2.getOrder();
                customer2 = null;
            } else if (kitchen2 == null) {
                kitchen2 = customer2.getOrder();
                customer2 = null;
            } else if (kitchen3 == null) {
                kitchen3 = customer2.getOrder();
                customer2 = null;
            } else {
                System.out.println("All kitchen slots are full!");
            }
        }

        if (customer3 != null) {
            if (kitchen1 == null) {
                kitchen1 = customer3.getOrder();
                customer3 = null;
            } else if (kitchen2 == null) {
                kitchen2 = customer3.getOrder();
                customer3 = null;
            } else if (kitchen3 == null) {
                kitchen3 = customer3.getOrder();
                customer3 = null;
            } else {
                System.out.println("All kitchen slots are full!");
            }
        }
    }

    private void assignCourier() {
        if (kitchen1 != null) {
            if (kitchen1.isFoodReady()) {
                if (courier1 == null) {
                    courier1 = new Courier(kitchen1);
                } else if (courier2 == null) {
                    courier2 = new Courier(kitchen1);
                } else if (courier3 == null) {
                    courier3 = new Courier(kitchen1);
                } else {
                    System.out.println("All courier slots are full!");
                }
            }
        }

        if (kitchen2 != null) {
            if (kitchen2.isFoodReady()) {
                if (courier1 == null) {
                    courier1 = new Courier(kitchen2);
                } else if (courier2 == null) {
                    courier2 = new Courier(kitchen2);
                } else if (courier3 == null) {
                    courier3 = new Courier(kitchen2);
                } else {
                    System.out.println("All courier slots are full!");
                }
            }
        }

        if (kitchen3 != null) {
            if (kitchen3.isFoodReady()) {
                if (courier1 == null) {
                    courier1 = new Courier(kitchen3);
                } else if (courier2 == null) {
                    courier2 = new Courier(kitchen3);
                } else if (courier3 == null) {
                    courier3 = new Courier(kitchen3);
                } else {
                    System.out.println("All courier slots are full!");
                }
            }
        }
    }

    public void startTick() {
        executor.scheduleAtFixedRate(() -> {
            gameTime.incrementAndGet();
            if (customer1 != null) {
                customer1.tick();
            }
            if (customer2 != null) {
                customer2.tick();
            }
            if (customer3 != null) {
                customer3.tick();
            }

            if (kitchen1 != null) {
                kitchen1.tick();
            }
            if (kitchen2 != null) {
                kitchen2.tick();
            }
            if (kitchen3 != null) {
                kitchen3.tick();
            }

            if (courier1 != null) {
                courier1.tick();
                if (courier1.doIt()) {
                    this.destroyKitchenFromFood(courier1.getFood());
                }
            }
            if (courier2 != null) {
                courier2.tick();
                if (courier2.doIt()) {
                    this.destroyKitchenFromFood(courier2.getFood());
                }
            }
            if (courier3 != null) {
                courier3.tick();
                if (courier3.doIt()) {
                    this.destroyKitchenFromFood(courier3.getFood());
                }
            }

            if (customer1 != null && customer1.isPatienceZero()) {
                drawInterface();
                System.out.println("Customer 1 left because their order was not ready in time!");
                customer1 = null;
            }

            if (customer2 != null && customer2.isPatienceZero()) {
                drawInterface();
                System.out.println("Customer 2 left because their order was not ready in time!");
                customer2 = null;
            }

            if (customer3 != null && customer3.isPatienceZero()) {
                drawInterface();
                System.out.println("Customer 3 left because their order was not ready in time!");
                customer3 = null;
            }

            if (courier1 != null && courier1.isDelivered()) {
                drawInterface();
                System.out.println("Courier 1 delivered the food!");
                courier1 = null;
            }

            if (courier2 != null && courier2.isDelivered()) {
                drawInterface();
                System.out.println("Courier 2 delivered the food!");
                courier2 = null;
            }

            if (courier3 != null && courier3.isDelivered()) {
                drawInterface();
                System.out.println("Courier 3 delivered the food!");
                courier3 = null;
            }

            drawInterface();
        }, 0, 1, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            int customers = 0;

            if (customer1 != null) {
                customers++;
            }
            if (customer2 != null) {
                customers++;
            }
            if (customer3 != null) {
                customers++;
            }

            if (customers < 3) {
                if (customer1 == null) {
                    customer1 = new Customer(main);
                } else if (customer2 == null) {
                    customer2 = new Customer(main);
                } else {
                    customer3 = new Customer(main);
                }
            }
        }, 15, 15, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            String input = main.getScanner().nextLine();
            if (input.toLowerCase().contains("q")) {
                this.stop();
                Menu.openMenu(MainMenu.class, null, main);
            } else if (input.toLowerCase().contains("p")) {
                this.prepareOrder();
            } else if (input.toLowerCase().contains("a")) {
                this.assignCourier();
            }
        }, 0, 125, TimeUnit.MILLISECONDS);
    }

    private void destroyKitchenFromFood(Food food) {
        if (food == null) {
            return;
        }

        if (kitchen1 != null && kitchen1 == food) {
            kitchen1 = null;
            return;
        }

        if (kitchen2 != null && kitchen2 == food) {
            kitchen2 = null;
            return;
        }

        if (kitchen3 != null && kitchen3 == food) {
            kitchen3 = null;
        }
    }


}
