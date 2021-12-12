package net.bean.java.gui.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public enum NumberOfCities {

    BENCHMARK(OptionalInt.empty(), "Benchmark - 100"), TEN(10, "10"), TWENTY(20, "20"), THIRTY(40, "40"), FIFTY(50, "50"), EIGHTY(80, "80"), HUNDRED(100, "100");

    private final OptionalInt numberOfCities;

    private final String description;

    NumberOfCities(OptionalInt numberOfCities, String description) {
        this.numberOfCities = numberOfCities;
        this.description = description;
    }

    NumberOfCities(int numberOfCities, String description) {
        this.numberOfCities = OptionalInt.of(numberOfCities);
        this.description = description;
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

}