package net.bean.java.tsp.algorithm.crossover;

import net.bean.java.tsp.algorithm.character.Individual;

import java.util.Arrays;
import java.util.Collection;

class CrossoverInputOutput<T> implements CrossoverInput<T>, CrossoverOutput<T> {

    private final Individual<T> first, second;

    CrossoverInputOutput(Individual<T> first, Individual<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Individual<T> getFirst() {
        return first;
    }

    @Override
    public Individual<T> getSecond() {
        return second;
    }

    @Override
    public Collection<Individual<T>> getAsCollection() {
        return Arrays.asList(first, second);
    }
}
