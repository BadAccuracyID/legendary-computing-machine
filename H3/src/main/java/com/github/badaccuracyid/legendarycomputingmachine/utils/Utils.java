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
        } catch (IOException | InterruptedException ignored) {
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        }
    }

    public static void printLCMLogo() {
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
        String logo = "   ____        _________  __        ____  __  ___                           \n" +
                "  / __/__  ___/_  __/ _ \\/ /  ___ _/ / / /  |/  /__ ____  ___ ____ ____ ____\n" +
                " / _// _ \\/ _ \\/ / / // / _ \\/ _ `/ / / / /|_/ / _ `/ _ \\/ _ `/ _ `/ -_) __/\n" +
                "/_/  \\___/\\___/_/ /____/_.__/\\_,_/_/_/ /_/  /_/\\_,_/_//_/\\_,_/\\_, /\\__/_/   \n" +
                "                                                             /___/          \n" +
                "\n";

        System.out.println(logo);
    }

}
