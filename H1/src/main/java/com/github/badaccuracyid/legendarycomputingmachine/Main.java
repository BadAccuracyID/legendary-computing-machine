package com.github.badaccuracyid.legendarycomputingmachine;

import com.github.badaccuracyid.legendarycomputingmachine.data.AnimeData;
import com.github.badaccuracyid.legendarycomputingmachine.database.Database;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Sorter;
import com.github.badaccuracyid.legendarycomputingmachine.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final Database database = new Database();

    public Main() {
        mainMenu();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void mainMenu() {
        int choice = 0;
        do {
            Utils.clearScreen();
            Utils.printLocalLogo();
            System.out.println("====================================================================================");
            System.out.println("1. View Anime List");
            System.out.println("2. Add new anime");
            System.out.println("3. Delete anime");
            System.out.println("4. Exit");
            System.out.print(">> ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1: {
                viewAnimeList();
                break;
            }
            case 2: {
                addNewAnime();
                break;
            }
            case 3: {
                deleteAnime();
                break;
            }
            case 4: {
                Utils.clearScreen();
                Utils.printLogo();
                System.exit(0);
                break;
            }
        }
    }

    private void viewAnimeList() {
        Utils.clearScreen();

        if (database.getAnimeDataList().isEmpty()) {
            System.out.println("There is no anime in the database!");
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            mainMenu();
            return;
        }

        System.out.println("Do you want to search by genre? [Yes/No]");
        System.out.print(">> ");
        String yesNo = scanner.nextLine();

        List<AnimeData> animeDataList;
        if (yesNo.equalsIgnoreCase("yes")) {
            System.out.println("Enter the genre you want to search for:");
            System.out.print(">> ");
            String genre = scanner.nextLine();

            animeDataList = database.getByGenre(genre);
        } else {
            animeDataList = database.getAnimeDataList();
        }

        if (animeDataList.isEmpty()) {
            Utils.clearScreen();
            System.out.println("There is no anime in the database!");
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            mainMenu();
            return;
        }

        Utils.clearScreen();
        Utils.printAnimeListLogo();
        Sorter.mergeSortPatients.accept(animeDataList);
        System.out.println("+=====+==========+=============================================================+");
        System.out.printf("| %-3s | %-8s | %-6s | %-50s |\n", "No.", "Anime ID", "Rating", "Anime Title");
        System.out.println("+=====+==========+=============================================================+");
        int i = 1;
        for (AnimeData animeData : animeDataList) {
            System.out.printf("| %-3s | %-8s | %-6.2f | %-50s |\n", i + ".", animeData.getId(), animeData.getRating(), animeData.getTitle());
            System.out.println("+=====+==========+=============================================================+");
            i++;
        }

        System.out.println();
        int choice = -1;
        do {
            System.out.print("Choose anime to view details ['0' to go back]: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (choice < 0 || choice > animeDataList.size());

        if (choice == 0) {
            mainMenu();
            return;
        }

        Utils.clearScreen();
        AnimeData animeData = animeDataList.get(choice - 1);
        System.out.printf("%-20s: %s\n", "Anime ID", animeData.getId());
        System.out.printf("%-20s: %s\n", "Anime Title", animeData.getTitle());
        System.out.printf("%-20s: %.2f\n", "Anime Rating", animeData.getRating());
        System.out.printf("%-20s: %s\n", "Anime Episodes", animeData.getEpisodes());
        System.out.printf("%-20s: %s\n", "Anime Description", animeData.getDescription());
        System.out.printf("%-20s: %s\n", "Anime Genre", animeData.getGenres().toString().replace("[", "").replace("]", ""));
        System.out.println();

        System.out.println("Do you want to add rating? [Yes/No]");
        System.out.print(">> ");
        yesNo = scanner.nextLine();
        if (yesNo.equalsIgnoreCase("yes")) {
            System.out.println();

            double rating = 0.0;
            do {
                System.out.print("Enter your rating [1 .. 10]: ");


                try {
                    rating = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException ignored) {
                }
            } while (rating < 1.0 || rating > 10.0);

            animeData.addRating(rating);
            System.out.println("Rating added! Current rating: " + animeData.getRating());
        }

        System.out.println("Press enter to continue...");
        scanner.nextLine();
        mainMenu();
    }

    private void addNewAnime() {
        Utils.clearScreen();

        String title;
        do {
            System.out.print("Enter anime title [5 .. 50 characters]: ");
            title = scanner.nextLine();
        } while (title.length() < 5 || title.length() > 50);

        String description;
        do {
            System.out.print("Enter anime description [>= 25 characters]: ");
            description = scanner.nextLine();
        } while (description.length() < 25);

        int episodes = -1;
        do {
            System.out.print("Enter anime episodes [>= 0]: ");
            try {
                episodes = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (episodes < 0);

        String releaseDate;
        do {
            System.out.print("Enter anime release date [dd-mm-yyyy]: ");
            releaseDate = scanner.nextLine();
        } while (!releaseDate.matches("\\d{2}-\\d{2}-\\d{4}"));

        int genreCount = 0;
        do {
            System.out.print("Enter anime genre count [>= 1]: ");
            try {
                genreCount = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (genreCount < 1);


        List<String> genres = new ArrayList<>();
        for (int i = 0; i < genreCount; i++) {
            System.out.print("Enter anime genre [" + (i + 1) + "]: ");
            String genre = scanner.nextLine();
            genres.add(genre);
        }

        String animeID;
        // AA[0-9][0-9][0-9]
        do {
            animeID = "AA" + Utils.randomInt(0, 9) + Utils.randomInt(0, 9) + Utils.randomInt(0, 9);
        } while (database.getAnimeByID(animeID) != null);

        AnimeData animeData = new AnimeData(animeID);
        animeData.setTitle(title);
        animeData.setDescription(description);
        animeData.setEpisodes(episodes);
        animeData.setReleaseDate(releaseDate);
        animeData.setGenres(genres);

        database.addAnime(animeData);

        System.out.println("Anime added!");
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        mainMenu();
    }

    private void deleteAnime() {
        Utils.clearScreen();

        if (database.getAnimeDataList().isEmpty()) {
            System.out.println("There is no anime in the database!");
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            mainMenu();
            return;
        }

        List<AnimeData> animeDataList = database.getAnimeDataList();
        Sorter.mergeSortPatients.accept(animeDataList);

        Utils.printAnimeListLogo();
        System.out.println("+=====+==========+=============================================================+");
        System.out.printf("| %-3s | %-8s | %-6s | %-50s |\n", "No.", "Anime ID", "Rating", "Anime Title");
        System.out.println("+=====+==========+=============================================================+");
        int i = 1;
        for (AnimeData animeData : animeDataList) {
            System.out.printf("| %-3s | %-8s | %-6.2f | %-50s |\n", i + ".", animeData.getId(), animeData.getRating(), animeData.getTitle());
            System.out.println("+=====+==========+=============================================================+");
            i++;
        }

        System.out.println();

        int choice = -1;
        do {
            System.out.print("Choose anime to delete ['0' to go back]: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }
        } while (choice < 0 || choice > animeDataList.size());

        if (choice == 0) {
            mainMenu();
            return;
        }

        AnimeData animeData = animeDataList.get(choice - 1);
        database.removeAnime(animeData);
        System.out.println("Anime deleted!");
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        mainMenu();
    }

}
