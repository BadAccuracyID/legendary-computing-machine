package com.github.badaccuracyid.legendarycomputingmachine.objects.requirements;

import java.util.function.Predicate;

public class StrRequirement {

    private final Predicate<String> predicate;
    private final String errorMessage;

    public StrRequirement(Predicate<String> predicate, String errorMessage) {
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }

    public Predicate<String> getPredicate() {
        return predicate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
