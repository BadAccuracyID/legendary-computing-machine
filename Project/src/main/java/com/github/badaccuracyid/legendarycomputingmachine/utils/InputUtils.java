package com.github.badaccuracyid.legendarycomputingmachine.utils;

import com.github.badaccuracyid.legendarycomputingmachine.Main;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.IntRequirement;
import com.github.badaccuracyid.legendarycomputingmachine.objects.requirements.StrRequirement;

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

            boolean valid = true;
            for (IntRequirement intRequirement : intRequirements) {
                if (!intRequirement.getPredicate().test(choice)) {
                    System.out.println(intRequirement.getErrorMessage());
                    valid = false;
                    break;
                }
            }

            if (valid) {
                break;
            }
        } while (true);

        return choice;
    };

    public static BiFunction<Runnable, List<StrRequirement>, String> determineStrChoice = (prompt, strRequirements) -> {
        String value;
        do {
            prompt.run();
            value = scanner.nextLine();

            boolean valid = true;
            for (StrRequirement strRequirement : strRequirements) {
                if (!strRequirement.getPredicate().test(value)) {
                    System.out.println(strRequirement.getErrorMessage());
                    valid = false;
                    break;
                }
            }

            if (valid) {
                break;
            }
        } while (true);

        return value;
    };
}
