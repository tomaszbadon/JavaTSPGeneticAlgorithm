package net.bean.java.gui.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SelectionType {

    RANKING("Ranking"), ROULETTE("Roulette");

    private String description;

    SelectionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String[] valuesAsString() {
        return Arrays.stream(SelectionType.values()).map(s -> s.description).toArray(String[]::new);
    }

    public static Optional<SelectionType> findByDescription(String description) {
        return Arrays.stream(SelectionType.values()).filter(s -> s.description.equals(description)).findFirst();
    }

}