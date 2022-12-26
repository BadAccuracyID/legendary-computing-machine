package com.github.badaccuracyid.legendarycomputingmachine.utils;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.objects.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.StrRequirement;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class InputUtils {

    private static final Scanner scanner = Main.getInstance().getScanner();

    public static BiFunction<Runnable, List<IntRequirement>, Integer> determineIntChoice = (prompt, intRequirements) -> {
        int choice = -1;
        do {
            prompt.run();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
            }

            for (IntRequirement intRequirement : intRequirements) {
                if (!intRequirement.getPredicate().test(choice)) {
                    System.out.println(intRequirement.getErrorMessage());
                    choice = -1;
                    break;
                }
            }
        } while (choice == -1);

        return choice;
    };

    public static BiFunction<Runnable, List<StrRequirement>, String> determineStrChoice = (prompt, strRequirements) -> {
        String value;
        do {
            prompt.run();
            value = scanner.nextLine();

            for (StrRequirement strRequirement : strRequirements) {
                if (!strRequirement.getPredicate().test(value)) {

                    System.out.println(strRequirement.getErrorMessage());
                    value = null;
                    break;
                }
            }
        } while (value == null);

        return value;
    };
}
