package com.github.badaccuracyid.legendarycomputingmachine.utils;

import java.io.IOException;

public class Utils {

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name");

            Process start;
            if (os.contains("windows")) {
                ProcessBuilder processBuilder;
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                start = processBuilder.start();
            } else {
                ProcessBuilder processBuilder;
                processBuilder = new ProcessBuilder("clear");
                start = processBuilder.inheritIO().start();
            }

            start.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printLogo() {
        String logo = "\n" +
                "                                  .......,*...                                  \n" +
                "                           ......&..........,%&&&*&.......                      \n" +
                "                         ...%.,.../(..&/,     .....,..(..,*....                 \n" +
                "                      ..#,......#*,..............   ..../%.....                 \n" +
                "                   .,/(.....(, .*....................  ...*                     \n" +
                "                  .,#,...,,. .........................  ,...% .                 \n" +
                "              ...&/((&#*,...............*&@@%...........  ,*,,....              \n" +
                "             ..(,*.... ./,.%,...... /&@@@@@ .............  .*,/...              \n" +
                "              ......./ .....*** &@@@@@&#.................. ..(,&                \n" +
                "                 (,.,....,..(@@@@@@/./@@@@@@@@@#*.......,*...,*.(               \n" +
                "                 %,,,..,,,.,.@@@@@@@#&......,@@@@@@@@@%.... .,/,%               \n" +
                "               ..%,,. ..,..,,,,../@@@@@@@@.../@@@@@#...... ..*,*(,              \n" +
                "               ..#... ..,,..,..........,,.(@@@@@ ......... ..../.,.             \n" +
                "                ./.... .. ......... .,&@@@@@%............. .*.,% /.             \n" +
                "                .  ...,#........... *@@@(.....,...........,(..#..,..            \n" +
                "                    .*. ,................,......,.......,&,,..                  \n" +
                "                     *.,.(...*......,.......,..,.. .  .&,..                     \n" +
                "                       ..,,,* . .*..,......    ...,%(...(,..                    \n" +
                "                        .,**#,*#,..(.#...,,,#&%###...*,*,...                    \n" +
                "                         . ...&&/.*&,.*&.......(#.                              \n" +
                "                                 ..*...,,..                                     \n" +
                "                                   .......                                       ";

        System.out.println(logo);
    }

    public static void printLocalLogo() {
        String logo = " __     __     _      _   _                 _                 ____     _____   \n" +
                " \\ \\   /\"/uU  /\"\\  u | \\ |\"|       ___     |\"|        ___    / __\"| u |_ \" _|  \n" +
                "  \\ \\ / //  \\/ _ \\/ <|  \\| |>     |_\"_|  U | | u     |_\"_|  <\\___ \\/    | |    \n" +
                "  /\\ V /_,-./ ___ \\ U| |\\  |u      | |    \\| |/__     | |    u___) |   /| |\\   \n" +
                " U  \\_/-(_//_/   \\_\\ |_| \\_|     U/| |\\u   |_____|  U/| |\\u  |____/>> u |_|U   \n" +
                "   //       \\\\    >> ||   \\\\,-.-,_|___|_,-.//  \\\\.-,_|___|_,-.)(  (__)_// \\\\_  \n" +
                "  (__)     (__)  (__)(_\")  (_/ \\_)-' '-(_/(_\")(\"_)\\_)-' '-(_/(__)    (__) (__) \n";

        System.out.println(logo);
    }

    public static void printAnimeListLogo() {
        String logo = "   __    _  _  ____  __  __  ____  __    ____  ___  ____ \n" +
                "  /__\\  ( \\( )(_  _)(  \\/  )( ___)(  )  (_  _)/ __)(_  _)\n" +
                " /(__)\\  )  (  _)(_  )    (  )__)  )(__  _)(_ \\__ \\  )(  \n" +
                "(__)(__)(_)\\_)(____)(_/\\/\\_)(____)(____)(____)(___/ (__) \n";

        System.out.println(logo);
    }

    public static String randomInt(int start, int end) {
        return String.valueOf((int) (Math.random() * (end - start + 1) + start));
    }
}
