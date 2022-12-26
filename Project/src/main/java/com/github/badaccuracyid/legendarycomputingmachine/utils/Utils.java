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
        String logo = " █████  ██   ██ ███████  ██████  ██████  ██████   █████  ████████ ████████ ██    ██ \n" +
                "██   ██  ██ ██  ██      ██    ██ ██   ██ ██   ██ ██   ██    ██       ██     ██  ██  \n" +
                "███████   ███   █████   ██    ██ ██████  ██████  ███████    ██       ██      ████   \n" +
                "██   ██  ██ ██  ██      ██    ██ ██   ██ ██      ██   ██    ██       ██       ██    \n" +
                "██   ██ ██   ██ ██       ██████  ██   ██ ██      ██   ██    ██       ██       ██    \n" +
                "                                                                                    \n" +
                "                                                                                    \n";

        System.out.println(logo);
    }

    public static int getRandomInt(int from, int to) {
        return (int) (Math.random() * (to - from + 1) + from);
    }
}
