package net.bean.java.gui.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public enum NumberOfCities {

    BENCHMARK(OptionalInt.empty(), "Benchmark - 100", 1800, 700),
    TEN(10, "10", 30, 100),
    TWENTY(20, "20", 120, 500),
    THIRTY(40, "40", 900, 400),
    FIFTY(50, "50", 1000, 500),
    EIGHTY(80, "80",1500, 600),
    HUNDRED(100, "100", 2000, 700);

    private final OptionalInt numberOfCities;
    private final String description;
    private final int defaultNumberOfIndividuals;
    private final int defaultNumberOfGenerations;

    NumberOfCities(OptionalInt numberOfCities, String description, int defaultNumberOfIndividuals, int defaultNumberOfGenerations) {
        this.numberOfCities = numberOfCities;
        this.description = description;
        this.defaultNumberOfIndividuals = defaultNumberOfIndividuals;
        this.defaultNumberOfGenerations = defaultNumberOfGenerations;
    }

    NumberOfCities(int numberOfCities, String description, int defaultNumberOfIndividuals, int defaultNumberOfGenerations) {
        this.numberOfCities = OptionalInt.of(numberOfCities);
        this.description = description;
        this.defaultNumberOfIndividuals = defaultNumberOfIndividuals;
        this.defaultNumberOfGenerations = defaultNumberOfGenerations;
    }

    public OptionalInt getNumberOfCities() {
        return numberOfCities;
    }

    public String getDescription() {
        return description;
    }

    public static String[] valuesAsString() {
        return Arrays.stream(NumberOfCities.values()).map(e -> e.description).toArray(size -> new String[size]);
    }

    public static Optional<NumberOfCities> findByDescription(String description) {
        return Arrays.stream(NumberOfCities.values()).filter(e -> e.description.equals(description)).findAny();
    }

    public int getDefaultNumberOfIndividuals() {
        return defaultNumberOfIndividuals;
    }

    public int getDefaultNumberOfGenerations() {
        return defaultNumberOfGenerations;
    }
}