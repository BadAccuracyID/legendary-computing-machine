package com.github.badaccuracyid.legendarycomputingmachine.objects;

import java.util.function.Predicate;

public class IntRequirement {

    private final Predicate<Integer> predicate;
    private final String errorMessage;

    public IntRequirement(Predicate<Integer> predicate, String errorMessage) {
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }

    public Predicate<Integer> getPredicate() {
        return predicate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
