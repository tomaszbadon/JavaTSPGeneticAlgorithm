package net.bean.java.tsp.algorithm.test;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class MockIndividual<T> implements Individual<T> {

    private final OptionalDouble valueOfFitnessFunction;

    private final List<T> chromosomes;

    public MockIndividual(double valueOfFitnessFunction) {
        this.valueOfFitnessFunction = OptionalDouble.of(valueOfFitnessFunction);
        this.chromosomes = Arrays.asList();
    }

    public MockIndividual(List<T> chromosomes) {
        this.valueOfFitnessFunction = OptionalDouble.empty();
        this.chromosomes = chromosomes;
    }

    @Override
    public List<T> getChromosomes() {
        return chromosomes;
    }

    @Override
    public double getValueOfFitnessFunction() {
        return valueOfFitnessFunction.orElseThrow(() -> new RuntimeException("The value of Fitness Function was not specified"));
    }

    @Override
    public int compareTo(Individual<T> o) {
        throw new RuntimeException("Method comparetTo() is not implemented in: " + this.getClass().getCanonicalName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockIndividual<?> that = (MockIndividual<?>) o;
        return Objects.equals(valueOfFitnessFunction, that.valueOfFitnessFunction) && Objects.equals(chromosomes, that.chromosomes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(valueOfFitnessFunction, chromosomes);
    }

    public static <T>IndividualBuilder<T> getIndividualBuilder() {
        return (chromosome) -> MockIndividual.of(chromosome);
    }

    public static <T>Individual<T> of(double valueOfFitnessFunction) { return new MockIndividual(valueOfFitnessFunction); }

    public static <T>Individual<T> of(List<T> chromosomes) { return new MockIndividual(chromosomes); }


}
